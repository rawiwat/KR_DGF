package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.damageCalculation
import com.example.kamenriderdesiregrandfighter.giveAGauge

class Ryuki: KamenRider(
    Constant.RYUKI,
    Constant.BASE_FORM,
    100,10,10,10,10,1,) {

    private class Survive:Move("Survive") {
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

    private class SwordVent: Move("Sword Vent") {
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
                intent.putExtra(Constant.HEALTH_DOWN, damage)
                if (damage > 0) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            }
        }
    }

    private class StrikeVent: Move("Strike Vent") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val changeTurn = Intent(Constant.TURN_CHANGE)
            changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
            context.sendBroadcast(changeTurn)
            if (user.form == Constant.BASE_FORM) {
                val intent = Intent(keyOpponent)
                intent.putExtra(Constant.HEALTH_DOWN, damageCalculation(user, opponent,1.5,0.5))
                context.sendBroadcast(intent)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN, 15)
                context.sendBroadcast(cost)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(SwordVent(), Survive(), StrikeVent())
        for (move in moveList) { moveSet.add(move) }
    }

}