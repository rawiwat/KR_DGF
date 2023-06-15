package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kamenriderdesiregrandfighter.Constant
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

    private class Survive:Move("Survive","4 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.FINAL_FORM && user.gauge >= 4) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.GAUGE_DOWN,4)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.ATTACK_SET,20)
                intent.putExtra(Constant.DEFENSE_SET,20)
                context.sendBroadcast(intent)
            }
        }
    }

    private class SwordVent: Move("Sword Vent","25 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(Constant.ENERGY_DOWN, 25)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            }
        }
    }

    private class StrikeVent: Move("Strike Vent","25 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(Constant.ENERGY_DOWN, 25)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.5,0.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            }
        }
    }

    private class Advent: Move("Advent","2 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= 2) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.GAUGE_DOWN,2)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,2.0,10.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            }
        }
    }

    private class GuardVent: Move("Guard Vent","25 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.ENERGY_DOWN,25)
                intent.putExtra(Constant.DEFENSE_SET,20)
                context.sendBroadcast(intent)
            }
        }
    }

    private class FinalVent: Move("Final Vent","5 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= 5) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.GAUGE_DOWN,5)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,6.0,10.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
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
            Fighter(kamenRider = Ryuki(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current)
            MovePanel(user = Ryuki(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}