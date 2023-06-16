package com.example.kamenriderdesiregrandfighter.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.compose.Fighter
import com.example.kamenriderdesiregrandfighter.compose.MovePanel
import com.example.kamenriderdesiregrandfighter.damageCalculation
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.giveAGauge
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

class Kabuto: KamenRider(
    Constant.KABUTO,
    Constant.BASE_FORM,
    95,8,10,50,95,11) {

    class ClockUp: Move("Clock Up","40 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if(user.form == Constant.BASE_FORM && user.energy >= 40 && user.speed <= 50) {
                var duration = 10
                val cooldownReceiver = object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        val counter = intent?.getStringExtra(Constant.TURN_CHANGE)
                        if (counter != null) {
                            duration -= 1
                            if (duration == 0 && user.form == Constant.BASE_FORM) {
                                val timeOut = Intent(keyUser)
                                timeOut.putExtra(Constant.SPEED_SET,50)
                                timeOut.putExtra(Constant.STATUS_MESSAGE,"CLOCK OVER")
                                context?.sendBroadcast(timeOut)
                            }
                        }
                    }
                }
                context.registerReceiver(cooldownReceiver, IntentFilter(Constant.TURN_CHANGE))
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.ENERGY_DOWN, 40)
                intent.putExtra(Constant.SPEED_SET, 250)
                intent.putExtra(Constant.STATUS_MESSAGE,"CLOCK UP!")
                intent.putExtra(Constant.IMAGE_ID, R.drawable.kabuto_clock_up)
                context.sendBroadcast(intent)
            } else if (user.form != Constant.BASE_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.BASE_FORM)))
                context.sendBroadcast(intent)
            } else if (user.energy < 40) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already clock up")
                context.sendBroadcast(intent)
            }
        }
    }

    private class MaskForm: Move("Put On","20 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context) {
            if (user.form == Constant.BASE_FORM && user.energy >= 20) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.UPGRADE_FORM)
                intent.putExtra(Constant.ENERGY_DOWN,20)
                intent.putExtra(Constant.ATTACK_SET,10)
                intent.putExtra(Constant.SPEED_SET,5)
                intent.putExtra(Constant.DEFENSE_SET,20)
                context.sendBroadcast(intent)
            } else if (user.form != Constant.BASE_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.BASE_FORM)))
                context.sendBroadcast(intent)
            }
        }
    }

    private class CastOff: Move("Cast Off", "") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form == Constant.UPGRADE_FORM) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.BASE_FORM)
                intent.putExtra(Constant.ATTACK_SET,8)
                intent.putExtra(Constant.SPEED_SET,50)
                intent.putExtra(Constant.DEFENSE_SET,10)
                intent.putExtra(Constant.IMAGE_ID,R.drawable.kabuto_cast_off)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.UPGRADE_FORM)))
                context.sendBroadcast(intent)
            }
        }
    }

    private class HyperForm: Move("Hyper Zector", "4 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form == Constant.BASE_FORM && user.gauge >= 4) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,4)
                intent.putExtra(Constant.ATTACK_SET,16)
                intent.putExtra(Constant.IMAGE_ID,R.drawable.kabuto_hyper_zector)
                context.sendBroadcast(intent)
            } else if (user.form != Constant.BASE_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.BASE_FORM)))
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class HyperClockUp: Move("Hyper ClockUp","40 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if(user.form == Constant.FINAL_FORM && user.energy >= 40 && user.speed <= 50) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.ENERGY_DOWN,40)
                intent.putExtra(Constant.SPEED_SET, 1000)
                intent.putExtra(Constant.IMAGE_ID,R.drawable.kabuto_hyper_clock_up)
                context.sendBroadcast(intent)
            } else if (user.form != Constant.FINAL_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO, Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            } else if (user.energy < 40) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already hyper clock up")
                context.sendBroadcast(intent)
            }
        }
    }

    private class KunaiGun: Move("Kunaigun","25 SP") {
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
                costIntent.putExtra(Constant.IMAGE_ID, if (user.form == Constant.UPGRADE_FORM) R.drawable.kabuto_kunai_gun_masked else R.drawable.kabuto_kunai_gun)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,2.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.dmg > 0 && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class RiderKick: Move("Rider Kick","3 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= 3 && user.form != Constant.UPGRADE_FORM) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.GAUGE_DOWN,3)
                cost.putExtra(Constant.IMAGE_ID,if (user.form == Constant.BASE_FORM) R.drawable.kabuto_rider_kick else R.drawable.kabuto_hyper_kick )
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,2.5,1.25)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.dmg > 0) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            } else if (user.gauge < 3) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE,"Mask form can't use rider kick")
                context.sendBroadcast(intent)
            }
        }
    }

    private class PerfectZector: Move("Perfect Zector","4 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= 4 && user.form == Constant.FINAL_FORM) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.GAUGE_DOWN,4)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.kabuto_perfect_zector)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,4.0,5.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            } else if (user.gauge < 4) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.KABUTO,Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            KunaiGun(),
            ClockUp(),
            MaskForm(),
            CastOff(),
            RiderKick(),
            HyperForm(),
            HyperClockUp(),
            PerfectZector()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun KabutoPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = Kabuto(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current)
            MovePanel(user = Kabuto(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}