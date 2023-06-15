package com.example.kamenriderdesiregrandfighter.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.damageCalculation

open class Move (val name: String) {

    open fun function(user:KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
    }

}

fun genericMoveSet(): List<Move> {

    val moveSet = mutableListOf<Move>()

    class Attack: Move("Attack") {
        override fun function(user: KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
            val intent = Intent(keyOpponent)
            val changeTurn = Intent(Constant.TURN_CHANGE)
            changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
            context.sendBroadcast(changeTurn)
            val damage = damageCalculation(user, opponent, 1.0, 1.0)
            intent.putExtra(Constant.HEALTH_DOWN, damage)
            if (damage > 0 && opponent.gauge < 5) {
                intent.putExtra(Constant.GAUGE_UP, 1)
            }
            context.sendBroadcast(intent)
        }
    }

    class ChargeGauge: Move("Charge RP") {
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
            if (user.gauge < 5) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.GAUGE_UP,1)
                context.sendBroadcast(intent)
            }
        }
    }

    class ChargeEnergy: Move("Charge SP") {
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
            val intent = Intent(keyUser)
            val missingEnergy = 100 - user.energy
            if (missingEnergy <= 10) {
                intent.putExtra(Constant.ENERGY_UP, missingEnergy)
            } else {
                intent.putExtra(Constant.ENERGY_UP, 10)
            }
            context.sendBroadcast(intent)
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
            println("Current Energy = $energy")
            val gaugeUp = intent.getIntExtra(Constant.GAUGE_UP,0)
            gauge += gaugeUp
            val gaugeDown = intent.getIntExtra(Constant.GAUGE_DOWN,0)
            gauge -= gaugeDown
            println("Current Gauge = $gauge")
            val attackSet = intent.getIntExtra(Constant.ATTACK_SET,attack)
            attack = attackSet
            val attackUp = intent.getIntExtra(Constant.ATTACK_UP,0)
            attack += attackUp
            val attackDown = intent.getIntExtra(Constant.ATTACK_DOWN, 0)
            attack -= attackDown
            println("Current Attack = $attack")
            val defenseSet = intent.getIntExtra(Constant.DEFENSE_SET,defense)
            defense = defenseSet
            val defenseUp = intent.getIntExtra(Constant.DEFENSE_UP,0)
            defense += defenseUp
            val defenseDown = intent.getIntExtra(Constant.DEFENSE_DOWN, 0)
            defense -= defenseDown
            println("Current Defense = $defense")
            val speedSet = intent.getIntExtra(Constant.SPEED_SET,speed)
            speed = speedSet
            val speedUp = intent.getIntExtra(Constant.SPEED_UP,0)
            speed += speedUp
            val speedDown = intent.getIntExtra(Constant.SPEED_DOWN, 0)
            speed -= speedDown
            println("Current Speed = $speed")
            val accuracySet = intent.getIntExtra(Constant.ACCURACY_SET,accuracy)
            accuracy = accuracySet
            val accuracyUp = intent.getIntExtra(Constant.ACCURACY_UP,0)
            accuracy += accuracyUp
            val accuracyDown = intent.getIntExtra(Constant.ACCURACY_DOWN, 0)
            accuracy -= accuracyDown
            println("Current Accuracy = $accuracy")
            val luckSet = intent.getIntExtra(Constant.LUCK_SET,luck)
            luck = luckSet
            val luckUp = intent.getIntExtra(Constant.LUCK_UP,0)
            luck += luckUp
            val luckDown = intent.getIntExtra(Constant.LUCK_DOWN, 0)
            luck -= luckDown
            println("Current Luck = $luck")
        }
    }

}

