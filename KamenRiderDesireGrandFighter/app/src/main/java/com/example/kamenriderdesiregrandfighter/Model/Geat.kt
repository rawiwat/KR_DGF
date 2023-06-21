package com.example.kamenriderdesiregrandfighter.Model

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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

class Geats: KamenRider(Constant.GEATS,
                        Constant.BASE_FORM,
                        100,10,10,52,90,1,
) {
    private class Magnum: Move("Magnum Shooter",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= cost) {
                val attackSound = MediaPlayer.create(context,R.raw.magnum_shooter)
                attackSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.geats_magnum)
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

    private class CommandForm: Move("Command Buckle",2, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context)
        {
            if (user.form != Constant.UPGRADE_FORM && user.gauge >= 2) {
                val commandSound = MediaPlayer.create(context, R.raw.command_twin_sound)
                commandSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.UPGRADE_FORM)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.geats_command)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,13)
                intent.putExtra(Constant.SPEED_SET,58)
                context.sendBroadcast(intent)
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already laser boost")
                context.sendBroadcast(intent)
            }
        }
    }

    private class LaserBoost: Move("Laser Boost",3, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.SUPER_FORM && user.gauge >= cost) {
                val laserBoostSound = MediaPlayer.create(context,R.raw.laser_boost_sound)
                laserBoostSound.start()
                val intent = Intent(keyUser)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                intent.putExtra(Constant.FORM_CHANGE,Constant.SUPER_FORM)
                intent.putExtra(Constant.IMAGE_ID,R.drawable.geats_laser)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,16)
                intent.putExtra(Constant.SPEED_SET,64)
                context.sendBroadcast(intent)
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already laser boost")
                context.sendBroadcast(intent)
            }
        }
    }

    private class GeatIX: Move("Mark IX",5, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.form != Constant.FINAL_FORM && user.gauge >= 5) {
                val geatsIXSound = MediaPlayer.create(context,R.raw.geat_ix_sound)
                geatsIXSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.geats_ix)
                intent.putExtra(Constant.ATTACK_SET,20)
                intent.putExtra(Constant.LUCK_UP,30)
                intent.putExtra(Constant.SPEED_SET,75)
                context.sendBroadcast(intent)
            } else if (user.gauge < 5) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, "already Geats IX")
                context.sendBroadcast(intent)
            }
        }
    }

    private class Creation: Move("Creation",80,Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= cost) {
                val chargeSound = MediaPlayer.create(context,R.raw.charge_sound)
                chargeSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(costType, cost)
                intent.putExtra(Constant.HEALTH_UP, 10)
                val rpGain = if (user.gauge >= Constant.MAX_GAUGE) {
                    0
                } else if (user.gauge == 9) {
                    1
                } else {
                    2
                }
                intent.putExtra(Constant.GAUGE_UP,rpGain)
                intent.putExtra(Constant.LUCK_UP,10)
                intent.putExtra(Constant.SET_MESSAGE,"HP+10\nRP+$rpGain")
                intent.putExtra(Constant.STATUS_MESSAGE,"Luck+10")
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Victory: Move("Desire Driver", 3,Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.gauge >= cost) {
                val baseFinish = MediaPlayer.create(context,R.raw.magnum_boost_finish)
                val upgradeFinish = MediaPlayer.create(context,R.raw.command_twin_finish)
                val superFinish = MediaPlayer.create(context,R.raw.laser_boost_finish)
                val finalFinish = MediaPlayer.create(context,R.raw.geat_ix_finish)
                when (user.form) {
                    Constant.BASE_FORM -> {
                        baseFinish.start()
                    }
                    Constant.UPGRADE_FORM -> {
                        upgradeFinish.start()
                    }
                    Constant.SUPER_FORM -> {
                        superFinish.start()
                    }
                    Constant.FINAL_FORM -> {
                        finalFinish.start()
                    }
                }
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                val imageId = when (user.form) {
                    Constant.BASE_FORM -> {
                        R.drawable.geats_magnum_boost_finish
                    }
                    Constant.UPGRADE_FORM -> {
                        R.drawable.geats_command_finish
                    }
                    Constant.SUPER_FORM -> {
                        R.drawable.geats_laser_boost_finish
                    }
                    else -> {
                        R.drawable.geats_ix_finish
                    }
                }
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, imageId)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,2.5,5.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
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

    private class TacticalBoost: Move("Boost Tactical",4, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            val boostTactical = MediaPlayer.create(context, R.raw.boost_tactical)
            if (user.gauge >= cost && user.form == Constant.FINAL_FORM) {
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.geats_ix_tactical_victory)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,4.5,3.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                boostTactical.start()
            } else if (user.gauge < cost) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.GEATS,Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            }
        }
    }

    init {
        val moveList: MutableList<Move> = mutableListOf(
            Magnum(),
            Victory(),
            Creation(),
            CommandForm(),
            LaserBoost(),
            GeatIX(),
            TacticalBoost()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun GeatsPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = Geats(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current,
                BorderStroke(3.dp, Color.Red)
            )
            MovePanel(user = Geats(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}