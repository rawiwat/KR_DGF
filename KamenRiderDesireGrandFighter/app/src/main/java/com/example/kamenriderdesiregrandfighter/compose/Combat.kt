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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

fun getRiderImage(kamenRider: KamenRider): Int {
var formImageID = 0
    if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.BASE_FORM) {
        formImageID = R.drawable.geats
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.UPGRADE_FORM) {
        formImageID = R.drawable.geats_upgrade
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.SUPER_FORM) {
        formImageID = R.drawable.geats_super
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.FINAL_FORM) {
        formImageID = R.drawable.geats_final
    } else if (kamenRider.name == Constant.RYUKI && kamenRider.form == Constant.BASE_FORM) {
        formImageID = R.drawable.ryuki
    } else if (kamenRider.name == Constant.RYUKI && kamenRider.form == Constant.FINAL_FORM) {
        formImageID = R.drawable.ryuki_final
    } else if (kamenRider.name == Constant.FAIZ && kamenRider.form == Constant.BASE_FORM) {
        formImageID = R.drawable.faiz
    } else if (kamenRider.name == Constant.FAIZ && kamenRider.form == Constant.FINAL_FORM) {
        formImageID = R.drawable.faiz_final
    } else if (kamenRider.name == Constant.KABUTO && kamenRider.form == Constant.BASE_FORM) {
        formImageID = R.drawable.kabuto
    } else if (kamenRider.name == Constant.KABUTO && kamenRider.form == Constant.FINAL_FORM) {
        formImageID = R.drawable.kabuto_final
    }

    return formImageID
}

fun getFormName(kamenRider: KamenRider): String {
    var formName = ""
    if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.BASE_FORM) {
        formName = "Magnum Boost"
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.UPGRADE_FORM) {
        formName = "Command"
    }



    return formName

}

fun getRiderFromName(name: String): KamenRider {
    var rider:KamenRider = if (name == Constant.GEATS) Geats()
    else Faiz()

    return rider
}
@Composable
fun CombatScreen(player1:String, player2: String, gameMode: String, navController: NavController) {

    val rider1 = getRiderFromName(player1)
    val rider2 = getRiderFromName(player2)
    Surface(modifier = Modifier.fillMaxSize()) {
        Row {
            Fighter(rider1, nameTag = if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1")
            Fighter(if (player2 != Constant.UNSELECTED) rider2 else getRiderFromName(RNG().getRandomRider()) , if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2")
        }

        BackHandler(enabled = true) {
            navController.navigate(route = Screen.Main.route)
        }
    }
}

@Composable
fun Fighter(kamenRider: KamenRider, nameTag: String) {
    var riderImage by rememberSaveable {
        mutableStateOf(getRiderImage(kamenRider))
    }
    val context = LocalContext.current
    val broadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SuspiciousIndentation")
        override fun onReceive(context: Context?, intent: Intent) {
            val currentForm = intent.getStringExtra(Constant.FORM_CHANGE)
            //riderImage = currentForm
        }
    }

    LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, IntentFilter(Constant.FIGHTER_SELECTION))

    Column {
        Text(text = nameTag)
        Text(text = "Kamen Rider ${kamenRider.name}")
        Text(text = "${getFormName(kamenRider)} form")
        Row() {
            Text(
                text = "HP",
                modifier = Modifier.height(15.dp),
                fontSize = 15.sp
            )
            HealthBar()
        }

        Row() {
            Text(
                text = "SP",
                modifier = Modifier.height(15.dp),
                fontSize = 15.sp
            )
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
    LinearProgressIndicator(
        modifier = Modifier
            .height(15.dp)
            .width(100.dp),
        progress = hp,
        color = colorResource(id = R.color.red)
    )
}

@Composable
fun StaminaBar() {
    val hp by rememberSaveable {
        mutableStateOf(1f)
    }
    LinearProgressIndicator(
        modifier = Modifier
            .height(15.dp)
            .width(100.dp),
        progress = hp,
        //color = colorResource(id = R.color.dark_blue)
    )
}
@Preview(showBackground = true)
@Composable
fun FighterPreview() {
    KamenRiderDesireGrandFighterTheme() {
        Fighter(Geats(), nameTag = "Player 1")
    }
}