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
import com.example.kamenriderdesiregrandfighter.getDoubleAttackMessage
import com.example.kamenriderdesiregrandfighter.getFormName
import com.example.kamenriderdesiregrandfighter.getFormRequire
import com.example.kamenriderdesiregrandfighter.getMessageIntent
import com.example.kamenriderdesiregrandfighter.giveAGauge
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

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
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_kataririba)
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
                val damage = damageCalculation(user, opponent, 1.25,2.5)
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
    private class Sagozo: Move("Sagozo",25, Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val sagozoSound = MediaPlayer.create(context, R.raw.sagorzou_sound)
                sagozoSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_sagozo)
                context.sendBroadcast(costIntent)
                val attack = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent, 1.5,0.5)
                attack.putExtra(Constant.HEALTH_DOWN,damage.dmg)
                if (damage.hit) {
                    attack.putExtra(Constant.DEFENSE_DOWN, 2)
                    attack.putExtra(Constant.STATUS_MESSAGE,"DEF-2!")
                }

                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                getMessageIntent(attack, damage)
                context.sendBroadcast(attack)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_SP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class Shauta: Move("Shauta",25,Constant.ENERGY_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {
            if (user.energy >= 25) {
                val shautaSound = MediaPlayer.create(context, R.raw.shauta_sound)
                shautaSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_shauta)
                context.sendBroadcast(costIntent)
                val attack = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent, 1.5,0.75)
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

    private class ScanningCharge: Move("Scanning Charge",3, Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {

            if (user.gauge >= 3) {
                val scanningCharge = MediaPlayer.create(context,R.raw.scaning_charge_shout)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID,if (user.form == Constant.FINAL_FORM) R.drawable.ooo_scanning_charge_final else if (user.form == Constant.SUPER_FORM) R.drawable.ooo_scanning_charge_super else R.drawable.ooo_scanning_charge)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,2.5,2.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit && opponent.gauge < Constant.MAX_GAUGE) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                scanningCharge.start()
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
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
                val tajadorSound = MediaPlayer.create(context,R.raw.ooo_tajadol_sound)
                tajadorSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.SUPER_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,14)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_tajador)
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
                val putotyraSound = MediaPlayer.create(context,R.raw.ooo_putotyra_sound)
                putotyraSound.start()
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val intent = Intent(keyUser)
                intent.putExtra(Constant.FORM_CHANGE,Constant.FINAL_FORM)
                intent.putExtra(costType,cost)
                intent.putExtra(Constant.ATTACK_SET,25)
                intent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_putotyra)
                context.sendBroadcast(intent)
            } else if (user.form == Constant.FINAL_FORM) {
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

    private class StrainDoom:Move("Strain Doom",4,Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {

            if(user.form == Constant.FINAL_FORM && user.gauge >= 4) {
                val burst = MediaPlayer.create(context,R.raw.strain_doom_sound)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.faiz_photon_breaker)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,5.0,2.5)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                burst.start()
            } else if (user.form != Constant.FINAL_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.OOO,Constant.FINAL_FORM)))
                context.sendBroadcast(intent)
            } else {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, Constant.NOT_ENOUGH_RP)
                context.sendBroadcast(intent)
            }
        }
    }

    private class GroundOfRage: Move("Ground Of Rage",4,Constant.GAUGE_DOWN) {
        override fun function(
            user: KamenRider,
            opponent: KamenRider,
            keyUser: String,
            keyOpponent: String,
            context: Context
        ) {

            if(user.form == Constant.FINAL_FORM && user.gauge >= 4) {
                val groundOfRageSound = MediaPlayer.create(context,R.raw.ground_of_rage_sound)
                val changeTurn = Intent(Constant.TURN_CHANGE)
                changeTurn.putExtra(Constant.TURN_CHANGE, keyOpponent)
                context.sendBroadcast(changeTurn)
                val costIntent = Intent(keyUser)
                costIntent.putExtra(costType,cost)
                costIntent.putExtra(Constant.IMAGE_ID, R.drawable.ooo_ground_of_rage)
                context.sendBroadcast(costIntent)
                val intent = Intent(keyOpponent)
                val damage = damageCalculation(user, opponent,4.5,2.0)
                intent.putExtra(Constant.HEALTH_DOWN, damage.dmg)
                getMessageIntent(intent, damage)
                if (damage.hit) {
                    giveAGauge(context, keyOpponent)
                }
                context.sendBroadcast(intent)
                groundOfRageSound.start()
            } else if (user.form != Constant.FINAL_FORM) {
                val intent = Intent(keyUser)
                intent.putExtra(Constant.STATUS_MESSAGE, getFormRequire(getFormName(Constant.OOO,Constant.FINAL_FORM)))
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
            Sagozo(),
            Shauta(),
            ScanningCharge(),
            Tajador(),
            Putotyra(),
            StrainDoom(),
            GroundOfRage()
        )
        for (move in moveList) { moveSet.add(move) }
    }
}

@Preview(showBackground = true)
@Composable
fun OOOPreview() {
    KamenRiderDesireGrandFighterTheme {
        Column {
            Fighter(kamenRider = OOO(), nameTag = Constant.PLAYER_ONE, playerKey = "", opponentKey = "", context = LocalContext.current,
                BorderStroke(3.dp, Color.Red)
            )
            MovePanel(user = OOO(), opponent = Kabuto(), keyUser = "", keyOpponent = "", context = LocalContext.current)
        }
    }
}