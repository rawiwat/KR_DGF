package com.example.kamenriderdesiregrandfighter.Model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

class Faiz:KamenRider(
    Constant.FAIZ,
    Constant.BASE_FORM,
    100,
    10,
    12,
    52,
    75,
    8,
) {
    class AutoVajin: Move("AutoVajin","20 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 20) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,20)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_auto_vajin)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.2)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    class FaizPhone: Move("Faiz Phone","20 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 20) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,20)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_phone)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.1,1.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                intent.putExtra(Constant.DEFENSE_DOWN, 2)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    intent.putExtra(Constant.DEFENSE_DOWN, 2)
                    giveAGauge(context, keyOpponent)
                    intent.putExtra(Constant.STATUS_MESSAGE,"DEF-2!")
                }
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    class FaizEdge: Move("Faiz Edge","25 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val exceedCharge = MediaPlayer.create(context,R.raw.exceed_charge)
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,25)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_edge)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.1)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    intent.putExtra(Constant.DEFENSE_DOWN, 2)
                    giveAGauge(context, keyOpponent)
                    intent.putExtra(Constant.STATUS_MESSAGE,"DEF-2!")
                }
                context.sendBroadcast(intent)
                exceedCharge.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    class FaizShot: Move("Faiz Shot","25 SP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val exceedCharge = MediaPlayer.create(context,R.raw.exceed_charge)
            if (user.energy >= 25) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,25)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_shot)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.1)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    intent.putExtra(Constant.DEFENSE_DOWN, 2)
                    giveAGauge(context, keyOpponent)
                    intent.putExtra(Constant.STATUS_MESSAGE,"DEF-2!")
                }
                context.sendBroadcast(intent)
                exceedCharge.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    class AxelForm: Move("Axelwatch","2 RP",) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val startUp = MediaPlayer.create(context,R.raw.start_up)
            val timeOutSound = MediaPlayer.create(context,R.raw.time_out)
            if (user.form == Constant.BASE_FORM && user.gauge >= 2) {
                var duration = 6
                val cooldownReceiver = object : BroadcastReceiver() {
                    override fun onReceive(context: Context?, intent: Intent?) {
                        val counter = intent?.getStringExtra(Constant.TURN_CHANGE)
                        if (counter != null) {
                            duration -= 1
                            if (duration == 0 && user.form == Constant.SUPER_FORM) {
                                val timeOut = Intent(keyUser)
                                timeOut.putExtra(Constant.SPEED_SET,53)
                                timeOut.putExtra(Constant.FORM_CHANGE,Constant.BASE_FORM)
                                context?.sendBroadcast(timeOut)
                                timeOutSound.start()
                            }
                        }
                    }
                }
                context.registerReceiver(cooldownReceiver, IntentFilter(Constant.TURN_CHANGE))
                val intent = Intent(keyUser)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                intent.putExtra(Constant.FORM_CHANGE, Constant.SUPER_FORM)
                intent.putExtra(Constant.GAUGE_DOWN, 2)
                intent.putExtra(Constant.SPEED_SET, 250)
                intent.putExtra(Constant.IMAGE_ID,R.drawable.faiz_axel)
                context.sendBroadcast(intent)
                startUp.start()
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

    class CrimsonSmash: Move("Exceed Charge","4 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val exceedCharge = MediaPlayer.create(context,R.raw.exceed_charge)
            if (user.gauge >= 4) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.GAUGE_DOWN,4)
                cost.putExtra(Constant.IMAGE_ID, if (user.form == Constant.BASE_FORM) R.drawable.faiz_pointer else if (user.form == Constant.SUPER_FORM) R.drawable.faiz_pointer_axel else R.drawable.faiz_pointer_blaster)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,4.5,3.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                exceedCharge.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Blaster: Move("Faiz Blaster", "4 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val awaken = MediaPlayer.create(context, R.raw.awakening)
            if (user.form == Constant.BASE_FORM && user.gauge >= 4) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(Constant.GAUGE_DOWN,4)
                intent.putExtra(Constant.ATTACK_SET,18)
                intent.putExtra(Constant.ACCURACY_SET,85)
                intent.putExtra(Constant.SPEED_SET,60)
                intent.putExtra(Constant.DEFENSE_SET,15)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.faiz_blaster)
                context.sendBroadcast(intent)
                awaken.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Breaker: Move("Photon Breaker", "1 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val burst = MediaPlayer.create(context,R.raw.burst_mode)
            if(user.form == Constant.FINAL_FORM && user.gauge >= 1) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,25)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_photon_breaker)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.5,1.1)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    intent.putExtra(Constant.DEFENSE_DOWN, 3)
                    giveAGauge(context, keyOpponent)
                    intent.putExtra(Constant.STATUS_MESSAGE,"DEF-3!")
                }
                context.sendBroadcast(intent)
                burst.start()
            } else if (user.form != Constant.FINAL_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.FAIZ,Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Buster: Move("Photon Buster", "1 RP") {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val burst = MediaPlayer.create(context,R.raw.burst_mode)
            if(user.form == Constant.FINAL_FORM && user.gauge >= 1) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val cost = Intent(keyUser)
                cost.putExtra(Constant.ENERGY_DOWN,25)
                cost.putExtra(Constant.IMAGE_ID, R.drawable.faiz_photon_buster)
                context.sendBroadcast(cost)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,1.25,1.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    intent.putExtra(Constant.DEFENSE_DOWN, 3)
                    giveAGauge(context, keyOpponent)
                    intent.putExtra(Constant.STATUS_MESSAGE,"DEF-3!")
                }
                context.sendBroadcast(intent)
                burst.start()
            } else if (user.form != Constant.FINAL_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.FAIZ,Constant.FINAL_FORM)))
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
            FaizPhone(),
            FaizEdge(),
            FaizShot(),
            AutoVajin(),
            AxelForm(),
            CrimsonSmash(),
            Blaster(),
            Buster(),
            Breaker()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun FaizPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = Faiz(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current, BorderStroke(3.dp, Color.Red))
            MovePanel(user = Faiz(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}