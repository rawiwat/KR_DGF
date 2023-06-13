package com.example.kamenriderdesiregrandfighter.compose

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.MainActivity
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.getImageIDFromName
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

@Composable
fun FighterSelectionScreen(
    gameMode: String,
    navController: NavController
) {

    val playableCharacters = listOf (
     Constant.RYUKI,
     Constant.FAIZ,
     Constant.KABUTO,
     Constant.OOO,
     Constant.GAIM,
     Constant.GEATS)

    var characterOne by rememberSaveable { mutableStateOf(Constant.UNSELECTED) }
    var characterTwo by rememberSaveable { mutableStateOf(Constant.UNSELECTED) }
    var playerOneConfirmed by rememberSaveable { mutableStateOf(false) }
    var playerTwoConfirmed by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(
                text = "Choose Your Fighter",
                fontSize = 30.sp
            )

            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(playableCharacters) {
                    RiderInSelection(
                        name = it,
                        modifier = Modifier
                        .clickable {
                        if (!playerOneConfirmed) {
                            characterOne = it
                            val intent = Intent(Constant.FIGHTER_SELECTION)
                            intent.putExtra(Constant.CHOSEN1, characterOne)
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)

                        } else if(!playerTwoConfirmed) {
                            characterTwo = it
                            val intent = Intent(Constant.FIGHTER_SELECTION)
                            intent.putExtra(Constant.CHOSEN2, characterTwo)
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
                        }
                                   }, context)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChosenRider(name = characterOne,if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1")
                Image(painter = painterResource(id = R.drawable.vs), contentDescription = null, modifier = Modifier.size(50.dp))
                ChosenRider(name = characterTwo,if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2")
            }

            Row {
                Button(
                    onClick = {
                        if (characterOne != Constant.UNSELECTED && gameMode == Constant.MULTI_PLAYER) {
                            playerOneConfirmed = true
                        } else if (characterOne != Constant.UNSELECTED && gameMode == Constant.SINGLE_PLAYER) {
                            playerOneConfirmed = true
                            playerTwoConfirmed = true
                        }
                              } ,
                    enabled = !playerOneConfirmed
                ) {
                    Text(text = "Confirm")
                }

                Spacer(modifier = Modifier.width(50.dp))

                Button(
                    onClick = {
                        if (characterTwo != Constant.UNSELECTED) playerTwoConfirmed = true
                    },
                    enabled = !playerTwoConfirmed) {
                    Text(text = "Confirm")
                }
            }
            Row {
                NaviButton (onClick = { navController.navigate(route = Screen.Main.route) },"Go Back")
                Spacer(modifier = Modifier.width(50.dp))
                NaviButton(
                    onClick = { navController.navigate(
                        route = Screen.Combat.passCombatSetting(
                            player1 = characterOne,
                            player2 = characterTwo,
                            gameMode = gameMode,
                        )
                    )
                              },"Fight")
            }
        }
    }
}

@Composable
fun RiderInSelection(name: String, modifier: Modifier, context: Context) {

    var chosenRider1 by rememberSaveable { mutableStateOf(Constant.UNSELECTED) }

    var chosenRider2 by rememberSaveable { mutableStateOf(Constant.UNSELECTED) }

    val broadcastReceiver = object : BroadcastReceiver() {

        @SuppressLint("SuspiciousIndentation")
        override fun onReceive(context: Context?, intent: Intent) {
            val selectedRider1 = intent.getStringExtra(Constant.CHOSEN1)
                if (selectedRider1 != null) {
                    chosenRider1 = selectedRider1
                }
            val selectedRider2 = intent.getStringExtra(Constant.CHOSEN2)
                if (selectedRider2 != null) {
                    chosenRider2 = selectedRider2
                }
            }
        }

    LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, IntentFilter(Constant.FIGHTER_SELECTION))

    Card(modifier = Modifier
        .padding(5.dp)
        .height(120.dp)
        .width(85.dp),
        border = BorderStroke(3.dp,
            if (chosenRider1 == name && chosenRider1 != chosenRider2) colorResource(id = R.color.red)
            else if (chosenRider2 == name && chosenRider1 != chosenRider2) colorResource (id = R.color.dark_blue)
            else if (chosenRider1 == name && chosenRider2 == name) colorResource(id = R.color.purple_500)
            else MaterialTheme.colorScheme.onSecondaryContainer
            )
    ) {
        Image(
            painter = painterResource(id = getImageIDFromName(name)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(5.dp),
        )
    }
}

@Composable
fun ChosenRider(name: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(300.dp)
            .padding(10.dp)
            .width(150.dp)
    ) {
        Text(
            text = text
        )
        Text(
            text = if (name == Constant.UNSELECTED)"Choose" else "Kamen Rider $name",
            maxLines = 1,
            fontSize = 15.sp
        )
        Surface(modifier = Modifier
            .height(250.dp)
            .width(175.dp)
        ) {
            Image(
                painter = painterResource(id = getImageIDFromName(name)),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun NaviButton(onClick: () -> Unit,text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(50.dp)
            .width(150.dp)
        ,
    ) {
        Text(
            text = text,
            fontSize = 25.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GeatsPreview() {
    KamenRiderDesireGrandFighterTheme {
        RiderInSelection(name = Constant.GEATS, Modifier, LocalContext.current)
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun FighterSelectionScreenPreview() {
    KamenRiderDesireGrandFighterTheme {
        FighterSelectionScreen(gameMode = Constant.SINGLE_PLAYER, navController = NavController(MainActivity()))
    }
}