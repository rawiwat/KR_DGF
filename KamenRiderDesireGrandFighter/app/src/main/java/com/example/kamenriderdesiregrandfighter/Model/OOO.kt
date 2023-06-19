package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.damageCalculation
import com.example.kamenriderdesiregrandfighter.getDoubleAttackMessage
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.giveAGauge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OOO: KamenRider(
    Constant.OOO,
    Constant.BASE_FORM,
115,9,11,10,80,13,) {

    private class Gatakiriba: Move("Gatakiriba",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val gatakiribaSound = MediaPlayer.create(context, R.raw.gatakiriba_sound)
                gatakiribaSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_ratorata)
                context.sendBroadcast(costIntent)
                val attack = Intent(keyOpponent)
                val damage1 = damageCalculation(user, opponent, 1.0,1.25)
                attack.putExtra(Constant.HEALTH_DOWN,damage1.dmg)
                if (damage1.hit) giveAGauge(context, keyOpponent)
                context.sendBroadcast(attack)
                val damage2 = damageCalculation(user, opponent, 1.0,1.25)
                attack.putExtra(Constant.HEALTH_DOWN,damage2.dmg)
                if (damage2.hit) giveAGauge(context, keyOpponent)
                getDoubleAttackMessage(attack, damage1, damage2)
                context.sendBroadcast(attack)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Ratorata: Move("Ratorata",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val ratorataSound = MediaPlayer.create(context, R.raw.ratoratah_sound)
                ratorataSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_ratorata)
                context.sendBroadcast(costIntent)
                val attack = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent, 1.3,1.25)
                attack.putExtra(Constant.HEALTH_DOWN,damage.dmg)
                if (damage.hit) giveAGauge(context, keyOpponent)
                getMessageIntent(attack, damage)
                context.sendBroadcast(attack)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }


    private class Tajador: Move("Tajador",3, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.SUPER_FORM && user.gauge >= 3) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.SUPER_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,14)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.kabuto_hyper_zector)
                context.sendBroadcast(intent)
            } else if (user.form == Constant.SUPER_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Tajador")
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Putotyra :Move("Putotyra",5, Constant.GAUGE_DOWN) {
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
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,25)
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
            Gatakiriba(),
            Ratorata(),
            Tajador(),
            Putotyra()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}