package com.example.kamenriderdesiregrandfighter.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.damageCalculation
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.getRiderImage
import com.example.kamenriderdesiregrandfighter.giveAGauge

open class Move (val name: String, val cost: Int, val costType: String) {

    open fun function(user:KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
    }
}

fun genericMoveSet(): List<Move> {

    val moveSet = mutableListOf<Move>()

    class Attack: Move("Attack",0, "") {
        override fun function(user: KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
            val attackSound = MediaPlayer.create(context,R.raw.normal_attack)
            attackSound.start()
            val changeTurn = Intent(Constant.TURN_CHANGE)
            changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
            context.sendBroadcast(changeTurn)
            val intent = Intent(keyUser)
            intent.putExtra(Constant.IMAGE_ID, getRiderImage(user.name,user.form))
            context.sendBroadcast(intent)
            val attack = Intent(keyOpponent)
            val damage = damageCalculation(user, opponent, 1.0, 1.0)
            attack.putExtra(Constant.HEALTH_DOWN, damage.dmg)
            getMessageIntent(attack, damage)
            if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                attack.putExtra(Constant.GAUGE_UP, 1)
            }
            context.sendBroadcast(attack)
        }
    }

    class ChargeGauge: Move("Charge RP",0,"") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge < Constant.MAX_GAUGE) {
                val chargeSound = MediaPlayer.create(context,R.raw.charge_sound)
                chargeSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.IMAGE_ID, getRiderImage(user.name,user.form))
                context.sendBroadcast(intent)
                giveAGauge(context, keyUser)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "RP Full")
                context.sendBroadcast(intent)
            }
        }
    }

    class ChargeEnergy: Move("Charge SP",0,"") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy < Constant.MAX_ENERGY) {
                val chargeSound = MediaPlayer.create(context,R.raw.charge_sound)
                chargeSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val missingSP = Constant.MAX_ENERGY - user.energy
                val intent = Intent(keyUser)
                intent.putExtra(Constant.IMAGE_ID, getRiderImage(user.name,user.form))
                if (missingSP >= 10) {
                    intent.putExtra(Constant.ENERGY_UP,10)
                } else {
                    intent.putExtra(Constant.ENERGY_UP,missingSP)
                }
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "SP Full")
                context.sendBroadcast(intent)
            }
        }
    }

    moveSet.add(Attack())
    moveSet.add(ChargeEnergy())
    moveSet.add(ChargeGauge())

    return moveSet
}

open class KamenRider (var name: String,
                       var form: String,
                       var health: Int,
                       var attack:Int,
                       var defense: Int,
                       var speed: Int,
                       var accuracy: Int,
                       var luck: Int,
                       var energy: Int = Constant.MAX_ENERGY,
                       var gauge: Int = 0,
                       var moveSet: MutableList<Move> = mutableListOf()
) {
    init {
        for (move in genericMoveSet()){
            moveSet.add(move)
        }
    }

    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val formChange = intent.getStringExtra(Constant.FORM_CHANGE)
            if (formChange != null) {
                form = formChange
            }
            val energyUp = intent.getIntExtra(Constant.ENERGY_UP,0)
            energy += energyUp
            val energyDown = intent.getIntExtra(Constant.ENERGY_DOWN, 0)
            energy -= energyDown
            if (energy < 0) energy = 0
            if (energy > Constant.MAX_ENERGY) energy = Constant.MAX_ENERGY
            println("Current Energy = $energy")
            val gaugeUp = intent.getIntExtra(Constant.GAUGE_UP,0)
            gauge += gaugeUp
            val gaugeDown = intent.getIntExtra(Constant.GAUGE_DOWN,0)
            gauge -= gaugeDown
            if (gauge < 0) gauge = 0
            if (gauge > Constant.MAX_GAUGE) gauge = Constant.MAX_GAUGE
            println("Current Gauge = $gauge")
            val attackSet = intent.getIntExtra(Constant.ATTACK_SET,attack)
            attack = attackSet
            val attackUp = intent.getIntExtra(Constant.ATTACK_UP,0)
            attack += attackUp
            val attackDown = intent.getIntExtra(Constant.ATTACK_DOWN, 0)
            attack -= attackDown
            if (attack < 0) attack = 0
            println("Current Attack = $attack")
            val defenseSet = intent.getIntExtra(Constant.DEFENSE_SET,defense)
            defense = defenseSet
            val defenseUp = intent.getIntExtra(Constant.DEFENSE_UP,0)
            defense += defenseUp
            val defenseDown = intent.getIntExtra(Constant.DEFENSE_DOWN, 0)
            defense -= defenseDown
            if (defense < 0) defense = 0
            println("Current Defense = $defense")
            val speedSet = intent.getIntExtra(Constant.SPEED_SET,speed)
            speed = speedSet
            val speedUp = intent.getIntExtra(Constant.SPEED_UP,0)
            speed += speedUp
            val speedDown = intent.getIntExtra(Constant.SPEED_DOWN, 0)
            speed -= speedDown
            if (speed < 0) speed = 0
            println("Current Speed = $speed")
            val accuracySet = intent.getIntExtra(Constant.ACCURACY_SET,accuracy)
            accuracy = accuracySet
            val accuracyUp = intent.getIntExtra(Constant.ACCURACY_UP,0)
            accuracy += accuracyUp
            val accuracyDown = intent.getIntExtra(Constant.ACCURACY_DOWN, 0)
            accuracy -= accuracyDown
            if (accuracy < 0) accuracy = 0
            println("Current Accuracy = $accuracy")
            val luckSet = intent.getIntExtra(Constant.LUCK_SET,luck)
            luck = luckSet
            val luckUp = intent.getIntExtra(Constant.LUCK_UP,0)
            luck += luckUp
            val luckDown = intent.getIntExtra(Constant.LUCK_DOWN, 0)
            luck -= luckDown
            if (luck < 0) luck = 0
            println("Current Luck = $luck")
        }
    }

}

