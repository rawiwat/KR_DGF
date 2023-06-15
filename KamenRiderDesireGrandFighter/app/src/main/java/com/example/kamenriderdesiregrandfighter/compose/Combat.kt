package com.example.kamenriderdesiregrandfighter.compose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.Model.Geats
import com.example.kamenriderdesiregrandfighter.Model.KamenRider
import com.example.kamenriderdesiregrandfighter.Model.Move
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getProgressBar
import com.example.kamenriderdesiregrandfighter.getRandomPlayer
import com.example.kamenriderdesiregrandfighter.getRandomRider
import com.example.kamenriderdesiregrandfighter.getRiderFromName
import com.example.kamenriderdesiregrandfighter.getRiderImage
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

@Composable
fun CombatScreen(player1:String, player2: String, gameMode: String, navController: NavController) {

    val context = LocalContext.current
    val mutablePlayer1 by rememberSaveable {
        mutableStateOf(player1)
    }

    val mutablePlayer2 by rememberSaveable {
        mutableStateOf(if (gameMode == Constant.MULTI_PLAYER) player2 else getRandomRider())
    }

    val rider1 = getRiderFromName(mutablePlayer1)

    context.registerReceiver(rider1.broadcastReceiver, IntentFilter(Constant.PLAYER_ONE))

    val rider2 = getRiderFromName(mutablePlayer2)

    context.registerReceiver(rider2.broadcastReceiver, IntentFilter(Constant.PLAYER_TWO))

    var gameOver by rememberSaveable {
        mutableStateOf(false)
    }

    var winner = ""
    val gameOverReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val gameState = intent.getBooleanExtra(Constant.GAME_OVER, false)
            gameOver = gameState
            val playerWon = intent.getStringExtra(Constant.WINNER)
            if (playerWon != null) {
                winner = playerWon
            }
        }
    }
    context.registerReceiver(gameOverReceiver, IntentFilter(Constant.GAME_OVER) )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row (horizontalArrangement = Arrangement.Center){
                Fighter(rider1, nameTag = if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1", Constant.PLAYER_ONE, Constant.PLAYER_TWO, context)
                Spacer(modifier = Modifier.width(10.dp))
                Fighter(rider2, if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2", Constant.PLAYER_TWO, Constant.PLAYER_ONE, context)
            }

            var currentTurn by rememberSaveable {
                mutableStateOf(getRandomPlayer())
            }

            DisposableEffect(currentTurn) {
                val turnBroadcastReceiver = object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent) {
                        val turnChanged = intent.getStringExtra(Constant.TURN_CHANGE)
                        if (turnChanged != null) {
                            currentTurn = turnChanged
                        }
                    }
                }
                context.registerReceiver(turnBroadcastReceiver,
                    IntentFilter(Constant.TURN_CHANGE) )

                onDispose {
                    context.unregisterReceiver(turnBroadcastReceiver)
                }
            }

            if (currentTurn == Constant.PLAYER_ONE && !gameOver) {
                Text(text = "Turn Player 1")
            } else if (currentTurn == Constant.PLAYER_TWO && !gameOver) {
                Text(text = "Turn Player 2")
            }
            if (currentTurn == Constant.PLAYER_ONE && !gameOver) {
                MovePanel(user = rider1, opponent = rider2,keyUser = Constant.PLAYER_ONE, keyOpponent = Constant.PLAYER_TWO, context)
            } else if (currentTurn == Constant.PLAYER_TWO && !gameOver){
                MovePanel(user = rider2, opponent = rider1, keyUser = Constant.PLAYER_TWO, keyOpponent = Constant.PLAYER_ONE, context)
            }
        }

        BackHandler(enabled = true) {
            navController.navigate(route = Screen.Main.route)
        }

        if(gameOver) GameOverMenu(winner = winner, navController = navController, player1, player2, gameMode)
    }
}

@Composable
fun Fighter(kamenRider: KamenRider, nameTag: String, playerKey: String, opponentKey: String, context: Context) {
    val maxHp = kamenRider.health

    val maxSp = Constant.MAX_ENERGY

    var riderForm by rememberSaveable {
        mutableStateOf(kamenRider.form)
    }

    var currentHealth by rememberSaveable {
        mutableStateOf(kamenRider.health)
    }

    var currentSp by rememberSaveable {
        mutableStateOf(kamenRider.energy)
    }

    var currentGauge by rememberSaveable {
        mutableStateOf(kamenRider.gauge)
    }

    DisposableEffect(currentHealth) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val healthUp = intent.getIntExtra(Constant.HEALTH_UP,0)
                currentHealth += healthUp
                val healthDown = intent.getIntExtra(Constant.HEALTH_DOWN, 0)
                currentHealth -= healthDown
                if (currentHealth <= 0) {
                    val gameState = Intent(Constant.GAME_OVER)
                    gameState.putExtra(Constant.GAME_OVER, true)
                    gameState.putExtra(Constant.WINNER, opponentKey)
                    context.sendBroadcast(gameState)
                }
                println("Current Health = $currentHealth")
            }
        }
        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    DisposableEffect(riderForm) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val formChange = intent.getStringExtra(Constant.FORM_CHANGE)
                if (formChange != null) {
                    riderForm = formChange
                }
            }
        }

        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))

        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    DisposableEffect(currentSp) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val energyUp = intent.getIntExtra(Constant.ENERGY_UP,0)
                currentSp += energyUp
                val energyDown = intent.getIntExtra(Constant.ENERGY_DOWN, 0)
                currentSp -= energyDown
                println("Current Energy Bar = $currentSp")
            }
        }
        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    DisposableEffect(currentGauge) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val gaugeUp = intent.getIntExtra(Constant.GAUGE_UP,0)
                currentGauge += gaugeUp
                val gaugeDown = intent.getIntExtra(Constant.GAUGE_DOWN, 0)
                currentGauge -= gaugeDown

                println("Current Gauge Bar= $currentGauge")
            }
        }

        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    Column {
        Text(text = nameTag)
        Text(text = "Kamen Rider ${kamenRider.name}")
        Text(text = "${getFormName(kamenRider.name, riderForm)} form")

        StatsBar(max = maxHp, current = currentHealth, text = "HP :", colorId = R.color.red)
        StatsBar(max = maxSp, current = currentSp, text = "SP :", colorId = R.color.teal_200)
        StatsBar(max = 5, current = currentGauge, text = "RP :", colorId = R.color.yellow)

        Image (painter = painterResource(
            id = getRiderImage(kamenRider.name, riderForm)
        ),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(175.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun StatsBar(max: Int, current: Int, text: String, colorId: Int) {

    Card(Modifier.width(120.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                modifier = Modifier.padding(3.dp),
                fontSize = 7.sp,
                color = colorResource(id = colorId)
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .height(15.dp)
                    .width(100.dp),
                progress = getProgressBar(max, current),
                color = colorResource(id = colorId)
            )
        }
    }
}

@Composable
fun MoveButton(user: KamenRider, opponent: KamenRider, move: Move, keyUser: String, keyOpponent: String, context: Context) {
    Button(
        onClick = { move.function(user, opponent, keyUser, keyOpponent, context) },
        modifier = Modifier.width(80.dp)
    ) {
        Text(
            text = move.name
        )
    }
}

@Composable
fun MovePanel(user: KamenRider, opponent: KamenRider, keyUser:String, keyOpponent: String, context: Context) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(user.moveSet) {
            MoveButton(
                user = user,
                opponent = opponent,
                move = it,
                keyUser = keyUser,
                keyOpponent = keyOpponent,
                context = context)
        }
    }
}

@Composable
fun GameOverMenu(winner: String, navController: NavController, player1: String, player2: String, gameMode: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Game Over!")
                Text(text = "$winner Won!")
                Row() {
                    Button(onClick = { navController.navigate(route = Screen.Main.route)}) {
                        Text(text = "Back to Main")
                    }
                    Button(onClick = { navController.navigate(route = Screen.Combat.passCombatSetting(player1, player2, gameMode))}) {
                        Text(text = "Rematch")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun GameOverPreview() {
    KamenRiderDesireGrandFighterTheme {
        GameOverMenu(Constant.PLAYER_ONE, NavController(LocalContext.current),"", "", "")
    }
}
@Preview(showBackground = true)
@Composable
fun FighterPreview() {
    KamenRiderDesireGrandFighterTheme {
        Fighter(Geats(), nameTag = "Player 1", "", "",LocalContext.current)
    }
}

@Preview(showBackground = true)
@Composable
fun CombatScreenPreview() {
    KamenRiderDesireGrandFighterTheme {
        CombatScreen(player1 = Constant.GEATS, player2 = Constant.RYUKI, gameMode = Constant.MULTI_PLAYER, navController = NavController(
            LocalContext.current)
        )
    }
}