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
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.giveAGauge
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

class Ryuki: KamenRider(
    Constant.RYUKI,
    Constant.BASE_FORM,
    110,10,13,10,100,1) {

    private class Survive:Move("Survive",4, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val survive_sound = MediaPlayer.create(context, R.raw.ryuki_survive)
            if (user.form != Constant.FINAL_FORM && user.gauge >= 4) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.GAUGE_DOWN,4)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.ATTACK_SET,20)
                intent.putExtra(Constant.DEFENSE_SET,20)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.survive)
                context.sendBroadcast(intent)
                survive_sound.start()
            } else if (user.gauge < 4) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class SwordVent: Move("Sword Vent",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val swordVentSound = MediaPlayer.create(context,R.raw.ryuki_sword_vent)
            val swordVentSurvive = MediaPlayer.create(context,R.raw.ryuki_sword_vent_survive)
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(Constant.ENERGY_DOWN, 25)
                costIntent.putExtra(Constant.IMAGE_ID,if (user.form == Constant.BASE_FORM) R.drawable.sword_vent else R.drawable.sword_vent_survive)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                if (user.form == Constant.BASE_FORM) {
                    swordVentSound.start()
                } else {
                    swordVentSurvive.start()
                }
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class StrikeVent: Move("Strike Vent",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val strikeVentSound = MediaPlayer.create(context,R.raw.ryuki_strike_vent)
            val strikeVentSurviveSound = MediaPlayer.create(context,R.raw.ryuki_strike_vent_survive)
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(Constant.ENERGY_DOWN, 25)
                costIntent.putExtra(Constant.IMAGE_ID,if (user.form == Constant.BASE_FORM) R.drawable.strike_vent else R.drawable.strike_vent_survive )
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.4,0.75)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                if (user.form == Constant.BASE_FORM) {
                    strikeVentSound.start()
                } else {
                    strikeVentSurviveSound.start()
                }
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    //done
    private class Advent: Move("Advent",2, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val advent = MediaPlayer.create(context,R.raw.ryuki_advent)
            val adventSurvive = MediaPlayer.create(context,R.raw.ryuki_advent_survive)
            if (user.gauge >= 2) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID,if (user.form == Constant.BASE_FORM) R.drawable.advent else R.drawable.advent_survive)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,2.0,10.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                if (user.form == Constant.BASE_FORM) {
                    advent.start()
                } else {
                    adventSurvive.start()
                }
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class GuardVent: Move("Guard Vent",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val guardVent = MediaPlayer.create(context,R.raw.ryuki_guard_vent)
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.DEFENSE_SET,20)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.guard_vent)
                context.sendBroadcast(intent)
                guardVent.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class FinalVent: Move(name = "Final Vent", cost = 5,costType = Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val finalVent = MediaPlayer.create(context,R.raw.ryuki_final_vent)
            val finalVentSurvive = MediaPlayer.create(context,R.raw.ryuki_final_vent_survive)
            if (user.gauge >= 5) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, if (user.form == Constant.BASE_FORM) R.drawable.final_vent else R.drawable.final_vent_survive)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,6.0,10.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                if (user.form == Constant.BASE_FORM) {
                    finalVent.start()
                } else {
                    finalVentSurvive.start()
                }
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            SwordVent(),
            StrikeVent(),
            GuardVent(),
            Advent(),
            FinalVent(),
            Survive(),)
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun RyukiPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = Ryuki(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current,
                BorderStroke(3.dp, Color.Red))
            MovePanel(user = Ryuki(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}