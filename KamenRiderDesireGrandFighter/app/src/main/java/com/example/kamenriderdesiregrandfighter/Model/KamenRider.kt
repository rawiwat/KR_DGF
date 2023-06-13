package com.example.kamenriderdesiregrandfighter.Model

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Constant

open class Move (val name: String) {
    open fun function(user:KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
    }
}
fun genericMoveSet(): List<Move> {

    val moveSet = mutableListOf<Move>()

    class Attack: Move("Attack") {
        override fun function(user: KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context) {
            val intent = Intent(keyOpponent)
            intent.putExtra(Constant.HEALTH_DOWN, user.attack)
            context.sendBroadcast(intent)
        }
    }

    class Block:Move("Block") {
        override fun function(user: KamenRider, opponent: KamenRider, keyUser: String, keyOpponent: String, context: Context
        ) {
           val intent = Intent(keyUser)
               intent.putExtra(Constant.DEFENSE_UP, user.defense)
           context.sendBroadcast(intent)
        }
    }
    moveSet.add(Attack())
    moveSet.add(Block())

    return moveSet
}

open class KamenRider (var name: String,
                       var form: String,
                       var health: Int,
                       var energy: Int,
                       var attack:Int,
                       var defense: Int,
                       var speed: Int,
                       var accuracy: Int,
                       var luck: Int,
                       var moveSet: MutableList<Move> = mutableListOf()
) {
    val broadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SuspiciousIndentation")
        override fun onReceive(context: Context?, intent: Intent) {
            val healthUp = intent.getIntExtra(Constant.HEALTH_UP,0)
            health += healthUp
            val healthDown = intent.getIntExtra(Constant.HEALTH_DOWN, 0)
            health -= healthDown
            val energyUp = intent.getIntExtra(Constant.ENERGY_UP,0)
            energy += energyUp
            val energyDown = intent.getIntExtra(Constant.ENERGY_DOWN,0)
            energy -= energyDown
            val attackUp = intent.getIntExtra(Constant.ATTACK_UP,0)
            attack += attackUp
            val attackDown = intent.getIntExtra(Constant.ATTACK_DOWN, 0)
            attack -= attackDown
            val defenseUp = intent.getIntExtra(Constant.DEFENSE_UP,0)
            defense += defenseUp
            val defenseDown = intent.getIntExtra(Constant.DEFENSE_DOWN,0)
            defense -= defenseDown
            val speedUp = intent.getIntExtra(Constant.SPEED_UP,0)
            speed += speedUp
            val speedDown = intent.getIntExtra(Constant.SPEED_DOWN, 0)
            speed -= speedDown
            val accuracyUp = intent.getIntExtra(Constant.ACCURACY_UP,0)
            accuracy += accuracyUp
            val accuracyDown = intent.getIntExtra(Constant.ACCURACY_DOWN,0)
            accuracy -= accuracyDown
            val luckUp = intent.getIntExtra(Constant.LUCK_UP,0)
            luck += luckUp
            val luckDown = intent.getIntExtra(Constant.LUCK_DOWN, 0)
            luck -= luckDown
        }
    }

    init {
        for (move in genericMoveSet()){
            moveSet.add(move)
        }
    }
}

