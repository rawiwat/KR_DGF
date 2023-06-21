package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.compose.Fighter
import com.example.kamenriderdesiregrandfighter.compose.MovePanel
import com.example.kamenriderdesiregrandfighter.damageCalculation
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.getRiderImage
import com.example.kamenriderdesiregrandfighter.giveAGauge
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme
import kotlin.math.cos

class Gaim: KamenRider(
    Constant.GAIM,
    Constant.BASE_FORM,
    105,9,11,42,80,12,) {

    private class ManOfBeginning: Move("Golden Fruit", 3, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= cost) {
                val chargeSound = MediaPlayer.create(context,R.raw.charge_sound)
                chargeSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(costType, cost)
                intent.putExtra(Constant.IMAGE_ID, getRiderImage(user.name,user.form))
                intent.putExtra(Constant.HEALTH_UP, 10)
                intent.putExtra(Constant.LUCK_UP,10)
                intent.putExtra(Constant.SET_MESSAGE,"HP+10")
                intent.putExtra(Constant.STATUS_MESSAGE,"Luck+10")
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Jimbra: Move("Jimbra Lemon",2, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val jimbraSound = MediaPlayer.create(context, R.raw.jimbra_lemon)
            if (user.form != Constant.UPGRADE_FORM && user.gauge >= cost) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.FORM_CHANGE,Constant.UPGRADE_FORM)
                intent.putExtra(Constant.ATTACK_SET,12)
                intent.putExtra(Constant.DEFENSE_SET,20)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.survive)
                context.sendBroadcast(intent)
                jimbraSound.start()
            } else if (user.gauge < 2) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Jimbra")
                context.sendBroadcast(intent)
            }
        }
    }

    private class Kadochiki: Move("Kadochiki Arms",3, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.SUPER_FORM && user.gauge >= cost) {
                val sound = MediaPlayer.create(context,R.raw.kachidoki_arms)
                sound.start()
                val intent = Intent(keyUser)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                intent.putExtra(Constant.FORM_CHANGE,Constant.SUPER_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,18)
                intent.putExtra(Constant.DEFENSE_SET,20)
                context.sendBroadcast(intent)
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Kachidoki")
                context.sendBroadcast(intent)
            }
        }
    }

    private class Kiwami: Move("Kiwami Arms",5, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.FINAL_FORM && user.gauge >= cost) {
                val kiwamiSound = MediaPlayer.create(context,R.raw.gaim_kiwami_arms)
                kiwamiSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,24)
                intent.putExtra(Constant.DEFENSE_SET,25)
                context.sendBroadcast(intent)
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Kiwami")
                context.sendBroadcast(intent)
            }
        }
    }

    private class KiwamiAuLait: Move("Kiwami Finish",4, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val sound = MediaPlayer.create(context, R.raw.kiwami_finisher)
            if (user.gauge >= cost && user.form == Constant.FINAL_FORM) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.kabuto_perfect_zector)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,4.5,2.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                sound.start()
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            ManOfBeginning(),
            Jimbra(),
            Kadochiki(),
            Kiwami(),
            KiwamiAuLait()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun GaimPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = Gaim(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current,
                BorderStroke(3.dp, Color.Red)
            )
            MovePanel(user = Gaim(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}