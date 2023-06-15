package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant

class Geats: KamenRider(Constant.GEATS,
                        Constant.BASE_FORM,
                        100,10,10,10,90,1,
) {
    private class CommandForm: Move("Command Buckle","2 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context) {
            if (user.form != Constant.UPGRADE_FORM && user.gauge >= 2) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.UPGRADE_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,2)
                intent.putExtra(Constant.ATTACK_UP,10)
                context.sendBroadcast(intent)
            }
        }
    }

    private class LaserBoost: Move("Laser Boost","3 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.SUPER_FORM && user.gauge >= 3) {
                val intent = Intent(keyUser)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                intent.putExtra(Constant.FORM_CHANGE,Constant.SUPER_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,3)
                intent.putExtra(Constant.ATTACK_SET,15)
                context.sendBroadcast(intent)
            }
        }
    }

    private class GeatIX: Move("Mark IX","5 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.FINAL_FORM && user.gauge >= 5) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,5)
                intent.putExtra(Constant.ATTACK_SET,25)
                intent.putExtra(Constant.LUCK_UP,30)
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(CommandForm(), LaserBoost(), GeatIX())
        for (move in moveList) { moveSet.add(move) }
    }

}