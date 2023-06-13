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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.Model.Faiz
import com.example.kamenriderdesiregrandfighter.Model.Geats
import com.example.kamenriderdesiregrandfighter.Model.KamenRider
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.RNG
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getRiderFromName
import com.example.kamenriderdesiregrandfighter.getRiderImage
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme
import java.util.jar.Attributes.Name


@Composable
fun CombatScreen(player1:String, player2: String, gameMode: String, navController: NavController) {

    val mutablePlayer1 by rememberSaveable {
        mutableStateOf(player1)
    }

    val mutablePlayer2 by rememberSaveable {
        mutableStateOf(if (gameMode == Constant.MULTI_PLAYER) player2 else RNG().getRandomRider())
    }
    val rider1 = getRiderFromName(mutablePlayer1)
    val rider2 = getRiderFromName(mutablePlayer2)
    Surface(modifier = Modifier.fillMaxSize()) {
        Row {
            Fighter(rider1, nameTag = if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1")
            Fighter(rider2 , if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2")
        }

        BackHandler(enabled = true) {
            navController.navigate(route = Screen.Main.route)
        }
    }
}

@Composable
fun Fighter(kamenRider: KamenRider, nameTag: String) {
    val riderImage by rememberSaveable {
        mutableStateOf(getRiderImage(kamenRider))
    }
    val context = LocalContext.current
    val broadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SuspiciousIndentation")
        override fun onReceive(context: Context?, intent: Intent) {
            //val currentForm = intent.getStringExtra(Constant.FORM_CHANGE)
            //riderImage = currentForm
        }
    }

    LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, IntentFilter(Constant.FIGHTER_SELECTION))

    Column {
        Text(text = nameTag)
        Text(text = "Kamen Rider ${kamenRider.name}")
        Text(text = "${getFormName(kamenRider)} form")
        Row() {
            HealthBar()
        }

        Row() {

            StaminaBar()
        }
        Image(painter = painterResource(
            id = riderImage),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(175.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun HealthBar() {
    val hp by rememberSaveable {
        mutableStateOf(1f)
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
            progress = hp,
            color = colorResource(id = R.color.red)
            )
        }
    }
}

@Composable
fun StaminaBar() {
    val sp by rememberSaveable {
        mutableStateOf(1f)
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
                progress = sp,
                color = colorResource(id = R.color.teal_200)
            )
        }
    }
}

@Composable
fun Move(onClick:() -> Unit, moveName: String) {
Button(onClick = onClick) {
    Text(
        text = moveName
    )
}
}
@Preview(showBackground = true)
@Composable
fun FighterPreview() {
    KamenRiderDesireGrandFighterTheme() {
        Fighter(Geats(), nameTag = "Player 1")
    }
}