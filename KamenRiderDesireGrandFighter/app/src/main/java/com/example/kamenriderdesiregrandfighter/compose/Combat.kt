package com.example.kamenriderdesiregrandfighter.compose

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.kamenriderdesiregrandfighter.RNG
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getProgressBar
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
        mutableStateOf(if (gameMode == Constant.MULTI_PLAYER) player2 else RNG().getRandomRider())
    }
    val rider1 = getRiderFromName(mutablePlayer1)
    val rider2 = getRiderFromName(mutablePlayer2)

    DisposableEffect(rider1) {
        context.registerReceiver(rider1.broadcastReceiver, IntentFilter(Constant.PLAYER_ONE))
        onDispose {
            context.unregisterReceiver(rider1.broadcastReceiver)
        }
    }

    DisposableEffect(rider2) {
        context.registerReceiver(rider2.broadcastReceiver, IntentFilter(Constant.PLAYER_TWO))
        onDispose {
            context.unregisterReceiver(rider2.broadcastReceiver)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            Row {
                Fighter(rider1, nameTag = if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1", Constant.PLAYER_ONE, context)
                Fighter(rider2 , if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2", Constant.PLAYER_TWO, context)
            }

            MovePanel(user = rider1, opponent = rider2,keyUser = Constant.PLAYER_ONE, keyOpponent = Constant.PLAYER_TWO, context)
            MovePanel(user = rider2, opponent = rider1, keyUser = Constant.PLAYER_TWO, keyOpponent = Constant.PLAYER_ONE, context)
        }
        BackHandler(enabled = true) {
            navController.navigate(route = Screen.Main.route)
        }
    }
}

@Composable
fun Fighter(kamenRider: KamenRider, nameTag: String, playerKey: String, context: Context) {

    var riderForm by rememberSaveable {
        mutableStateOf(getRiderImage(kamenRider.name, kamenRider.form))
    }

    DisposableEffect(riderForm) {
        val broadcastReceiver = object : BroadcastReceiver() {
            @SuppressLint("SuspiciousIndentation")
            override fun onReceive(context: Context?, intent: Intent) {
                val formChange = intent.getStringExtra(Constant.FORM_CHANGE)
                if (formChange != null) {
                    riderForm = getRiderImage(kamenRider.name, formChange)
                }
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
        Text(text = "${getFormName(kamenRider)} form")
        Row {
            HealthBar(kamenRider, context, playerKey)
        }

        Row {
            StaminaBar(kamenRider, context, playerKey)
        }
        Image(painter = painterResource(
            id = riderForm
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
fun HealthBar(kamenRider: KamenRider, context: Context, playerKey: String) {
    val maxHp = kamenRider.health
    var currentHp by rememberSaveable {
        mutableStateOf(kamenRider.health)
    }
    DisposableEffect(currentHp) {
        val broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {
                val healthUp = intent.getIntExtra(Constant.HEALTH_UP,0)
                currentHp += healthUp
                val healthDown = intent.getIntExtra(Constant.HEALTH_DOWN,0)
                currentHp -= healthDown
                println("Current Hp is : $currentHp")
            }
        }

        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))

        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    Card(Modifier.width(120.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "HP :",
                modifier = Modifier.padding(3.dp),
                fontSize = 7.sp,
                color = colorResource(id = R.color.red)
            )
            LinearProgressIndicator(
                    modifier = Modifier
                        .height(15.dp)
                        .width(100.dp),
            progress = getProgressBar(maxHp, currentHp),
            color = colorResource(id = R.color.red)
            )
        }
    }
}

@Composable
fun StaminaBar(kamenRider: KamenRider, context: Context, playerKey: String) {
    val maxSp = kamenRider.energy
    var currentSp by rememberSaveable {
        mutableStateOf(kamenRider.energy)
    }
    DisposableEffect(currentSp) {
        val broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {
                val energyUp = intent.getIntExtra(Constant.ENERGY_UP,0)
                currentSp += energyUp
                val energyDown = intent.getIntExtra(Constant.ENERGY_DOWN,0)
                currentSp -= energyDown
                println("Current Sp is : $currentSp")
            }
        }

        context.registerReceiver(broadcastReceiver, IntentFilter(playerKey))
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    Card(Modifier.width(120.dp)) {
        Row() {
            Text(
                text = "SP :",
                modifier = Modifier.padding(3.dp),
                fontSize = 7.sp,
                color = colorResource(id = R.color.teal_200)
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .height(15.dp)
                    .width(100.dp),
                progress = getProgressBar(maxSp ,currentSp),
                color = colorResource(id = R.color.teal_200)
            )
        }
    }
}

@Composable
fun MoveButton(user: KamenRider, opponent: KamenRider, move: Move, keyUser: String, keyOpponent: String, context: Context) {
Button(
    onClick = { move.function(user, opponent, keyUser, keyOpponent,context) },
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

@Preview(showBackground = true)
@Composable
fun FighterPreview() {
    KamenRiderDesireGrandFighterTheme() {
        Fighter(Geats(), nameTag = "Player 1", "", LocalContext.current)
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