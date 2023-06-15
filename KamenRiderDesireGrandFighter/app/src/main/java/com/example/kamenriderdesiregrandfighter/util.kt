package com.example.kamenriderdesiregrandfighter

import android.content.Context
import android.content.Intent
import com.example.kamenriderdesiregrandfighter.Model.Faiz
import com.example.kamenriderdesiregrandfighter.Model.Gaim
import com.example.kamenriderdesiregrandfighter.Model.Geats
import com.example.kamenriderdesiregrandfighter.Model.Kabuto
import com.example.kamenriderdesiregrandfighter.Model.KamenRider
import com.example.kamenriderdesiregrandfighter.Model.OOO
import com.example.kamenriderdesiregrandfighter.Model.Ryuki
import kotlin.random.Random

fun getImageIDFromName(name: String): Int {
    val id = when (name) {
        Constant.GEATS -> {
            R.drawable.geats
        }
        Constant.RYUKI -> {
            R.drawable.ryuki
        }
        Constant.KABUTO -> {
            R.drawable.kabuto
        }
        Constant.GAIM -> {
            R.drawable.gaim
        }
        Constant.FAIZ -> {
            R.drawable.faiz
        }
        Constant.OOO -> {
            R.drawable.ooo
        }
        else -> {
            R.drawable.krlogo
        }
    }
    return id
}

fun getRiderImage(kamenRiderName: String, kamenRiderForm: String): Int {
    var formImageID = 0
    if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.geats
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.UPGRADE_FORM) {
        formImageID = R.drawable.geats_upgrade
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.SUPER_FORM) {
        formImageID = R.drawable.geats_super
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.geats_final
    } else if (kamenRiderName == Constant.RYUKI && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.ryuki
    } else if (kamenRiderName == Constant.RYUKI && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.ryuki_final
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.faiz
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.faiz_final
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.kabuto
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.kabuto_final
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.gaim
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.UPGRADE_FORM) {
        formImageID = R.drawable.gaim_upgrade
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.SUPER_FORM) {
        formImageID = R.drawable.gaim_super
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.gaim_final
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.ooo
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.SUPER_FORM) {
        formImageID = R.drawable.ooo_super
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.ooo_final
    }

    return formImageID
}

fun getFormName(kamenRiderName: String, kamenRiderForm: String): String {
    val formName: String
    if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.BASE_FORM) {
        formName = "Magnum Boost"
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.UPGRADE_FORM) {
        formName = "Command"
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.SUPER_FORM) {
        formName = "Laser Boost"
    } else if (kamenRiderName == Constant.GEATS && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "IX"
    } else if (kamenRiderName == Constant.RYUKI && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "Survive"
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.SUPER_FORM) {
        formName = "Axel"
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "Blaster"
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.UPGRADE_FORM) {
        formName = "Masked"
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "Hyper"
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.BASE_FORM) {
        formName = "Orange"
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.UPGRADE_FORM) {
        formName = "Jimbra Lemon"
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.SUPER_FORM) {
        formName = "Kachidoki"
    } else if (kamenRiderName == Constant.GAIM && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "Kiwami"
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.BASE_FORM) {
        formName = "Tatoba"
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.SUPER_FORM) {
        formName = "Tajador"
    } else if (kamenRiderName == Constant.OOO && kamenRiderForm == Constant.FINAL_FORM) {
        formName = "Putotyra"
    } else {
        formName = "Rider"
    }

    return formName

}

fun getRiderFromName(name: String): KamenRider {
    val rider:KamenRider =
        if (name == Constant.GEATS) Geats()
        else if (name == Constant.FAIZ) Faiz()
        else if (name == Constant.RYUKI) Ryuki()
        else if (name == Constant.KABUTO) Kabuto()
        else if (name == Constant.OOO) OOO()
        else Gaim()

    return rider
}

fun getProgressBar(max: Int, current: Int): Float {
    var result = (current.toFloat() / max.toFloat())
    if (result < 0) {
        result = 0f
    }
    println("result = $result")
    return result
}

fun damageCalculation(user: KamenRider, opponent: KamenRider, powerMultiplier: Double, accuracyMultiplier: Double):Int {
    val powerGap = if (user.attack > opponent.defense) {
        user.attack - opponent.defense
    } else if (user.attack < opponent.defense) {
        opponent.defense - user.attack
    } else {
        0
    }

    val luckGap = if (user.luck > opponent.luck) {
        user.luck - opponent.luck
    } else {
        10
    }

    val critChance = luckGap / 10
    val speedGap = if (user.speed > opponent.speed) {
        user.speed - opponent.speed
    } else if (user.speed < opponent.speed) {
        opponent.speed - user.speed
    } else {
        0
    }

    var result = 0
    val crit:Boolean
    val possibleOutcome = listOf(true,false)
    val randomizingOutComeHit = mutableListOf<Boolean>()
    val randomizingOutComeCrit = mutableListOf<Boolean>()

    for (outcome in possibleOutcome) {
        val randomHit = if(outcome) user.speed + opponent.speed + user.luck + opponent.luck + (user.accuracy * accuracyMultiplier).toInt() else speedGap + luckGap
        val randomCrit = if(outcome) critChance else user.luck + opponent.luck
        repeat(randomHit) { randomizingOutComeHit.add(outcome) }
        repeat(randomCrit) { randomizingOutComeCrit.add(outcome) }
    }

    val hit = randomizingOutComeHit.random()
    crit = randomizingOutComeCrit.random()

    if (hit && crit) {
        result = if (user.attack > opponent.defense) {
            (user.attack * powerMultiplier * 2 + Random.nextInt(powerGap)).toInt()
        } else (user.attack * powerMultiplier * 2).toInt()
    } else if (hit) {
        result = if (user.attack > opponent.defense) {
            (user.attack * powerMultiplier + Random.nextInt(powerGap)).toInt()
        } else if (user.attack < opponent.defense) {
            (user.attack * powerMultiplier - Random.nextInt(powerGap)).toInt()
        } else (user.attack * powerMultiplier).toInt()
    }

    if (result < 0) {
        result = 0
    }

    return result
}


fun getRandomRider(): String {
    val playableCharacters = listOf(Constant.GEATS,Constant.OOO,Constant.GAIM,Constant.FAIZ,Constant.KABUTO,Constant.RYUKI)
    return playableCharacters.random()
}

fun getRandomPlayer(): String {
    val playerList = listOf(Constant.PLAYER_ONE, Constant.PLAYER_TWO)
    return playerList.random()
}

fun giveAGauge(context: Context, key: String) {
    val intent = Intent(key)
    intent.putExtra(Constant.GAUGE_UP,1)
    context.sendBroadcast(intent)
}