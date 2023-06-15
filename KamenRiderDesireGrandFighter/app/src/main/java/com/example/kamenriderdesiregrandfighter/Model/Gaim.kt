package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant

class Gaim: KamenRider(
    Constant.GAIM,
    Constant.BASE_FORM,
    105,9,11,10,80,12) {

    private class Jimbra:Move("Jimbra","2 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
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

    private class Kadochiki: Move("Kadochiki","3 RP") {
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

    private class Kiwami: Move("Golden Fruit","5 RP") {
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
                intent.putExtra(Constant.ATTACK_SET,20)
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            Jimbra(),
            Kadochiki(),
            Kiwami()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}