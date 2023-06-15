package com.example.kamenriderdesiregrandfighter.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.ui.platform.LocalContext
import com.example.kamenriderdesiregrandfighter.Constant

class Kabuto: KamenRider(
    Constant.KABUTO,
    Constant.BASE_FORM,
    100,7,10,10,20,1) {

    class ClockUp: Move("Clock Up") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val duration = 4
            val durationBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val durationDown = intent?.getStringExtra(Constant.TURN_CHANGE)
                if (durationDown == keyOpponent) {
                    duration - 1
                }
                if (duration == 0) {
                    val clockOver = Intent(keyUser)
                    clockOver.putExtra(Constant.SPEED_SET,10)
                    context?.sendBroadcast(clockOver)
                }
            }
        }

            if(user.energy >= 40 && user.speed <= 10) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.ENERGY_DOWN, 40)
                intent.putExtra(Constant.SPEED_SET, 150)
                context.sendBroadcast(intent)
                context.registerReceiver(durationBroadcastReceiver, IntentFilter(keyUser))
            }

        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            ClockUp()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}