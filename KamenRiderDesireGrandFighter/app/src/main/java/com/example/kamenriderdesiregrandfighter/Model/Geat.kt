package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant

class Geats: KamenRider(Constant.GEATS,
                        Constant.BASE_FORM,
                        100,100,10,10,10,1,1
) {
    class CommandForm:Move("CommandForm") {
        override fun function(user: KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
            val intent = Intent(keyUser)
            intent.putExtra(Constant.FORM_CHANGE,Constant.UPGRADE_FORM)
            intent.putExtra(Constant.ENERGY_DOWN,20)
            intent.putExtra(Constant.ATTACK_UP,10)
            context.sendBroadcast(intent)
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(CommandForm())
        for (move in moveList) { moveSet.add(move) }
    }

}