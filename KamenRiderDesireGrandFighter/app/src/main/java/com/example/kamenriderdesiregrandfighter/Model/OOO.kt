package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire

class OOO: KamenRider(
    Constant.OOO,
    Constant.BASE_FORM,
115,9,12,10,80,13,) {

    private class Tajador :Move("Tajador","3RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {

        }
    }

    private class Putotyra :Move("Tajador","4RP") {
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
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,4)
                intent.putExtra(Constant.ATTACK_SET,16)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.kabuto_hyper_zector)
                context.sendBroadcast(intent)
            } else if (user.form != Constant.BASE_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Putotyra")
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            Tajador(),
            Putotyra()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}