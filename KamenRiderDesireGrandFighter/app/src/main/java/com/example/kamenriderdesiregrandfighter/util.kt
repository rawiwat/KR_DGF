package com.example.kamenriderdesiregrandfighter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
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
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.SUPER_FORM) {
        formImageID = R.drawable.faiz_super
    } else if (kamenRiderName == Constant.FAIZ && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.faiz_final
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.BASE_FORM) {
        formImageID = R.drawable.kabuto
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.FINAL_FORM) {
        formImageID = R.drawable.kabuto_final
    } else if (kamenRiderName == Constant.KABUTO && kamenRiderForm == Constant.UPGRADE_FORM) {
        formImageID = R.drawable.kabuto_upgrade
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
        when (name) {
            Constant.GEATS -> Geats()
            Constant.FAIZ -> Faiz()
            Constant.RYUKI -> Ryuki()
            Constant.KABUTO -> Kabuto()
            Constant.OOO -> OOO()
            else -> Gaim()
        }
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

data class DMGresult(val dmg: Int, val hit:Boolean, val crit:Boolean)

fun damageCalculation(
    user: KamenRider,
    opponent: KamenRider,
    powerMultiplier: Double,
    accuracyMultiplier: Double
): DMGresult {

    val luckGap = if (user.luck > opponent.luck) {
        user.luck - opponent.luck
    } else if (user.luck < opponent.luck) {
        opponent.luck - user.luck
    } else {
        0
    }

    var dodgeChance = (opponent.speed + opponent.luck) / 10

    var critChance = (user.luck) / 10

    val speedGap = if (user.speed > opponent.speed) {
        user.speed - opponent.speed
    } else if (user.speed < opponent.speed) {
        opponent.speed - user.speed
    } else {
        0
    }

    if (user.luck >= opponent.luck) {
        dodgeChance += luckGap
        critChance += luckGap
    } else {
        dodgeChance -= luckGap
        critChance -= luckGap
    }

    if (user.speed >= opponent.speed) dodgeChance + speedGap else dodgeChance - speedGap

    var result = 0
    val possibleOutcome = listOf(true, false)
    val randomizingOutComeHit = mutableListOf<Boolean>()
    val randomizingOutComeCrit = mutableListOf<Boolean>()

    for (outcome in possibleOutcome) {
        val randomHit =
            if (outcome) (user.accuracy * accuracyMultiplier).toInt() + user.speed else dodgeChance
        val randomCrit = if (outcome) critChance else opponent.luck
        repeat(randomHit) { randomizingOutComeHit.add(outcome) }
        repeat(randomCrit) { randomizingOutComeCrit.add(outcome) }
    }

    val hit = randomizingOutComeHit.random()
    val crit = randomizingOutComeCrit.random()

    if (hit && crit) {
        result = (user.attack * powerMultiplier * 2).toInt()
    } else if (hit) {
        result = (user.attack * powerMultiplier).toInt() - Random.nextInt(opponent.defense)
    }

    if (result < 0) {
        result = 0
    }
    return DMGresult(result, hit, crit)
}

fun getRandomRider(): String {
    val playableCharacters = listOf(
        Constant.GEATS,
        Constant.OOO,
        Constant.GAIM,
        Constant.FAIZ,
        Constant.KABUTO,
        Constant.RYUKI
    )
    return playableCharacters.random()
}

fun getRandomPlayer(): String {
    val playerList = listOf(Constant.PLAYER_ONE, Constant.PLAYER_TWO)
    return playerList.random()
}

fun giveAGauge(context: Context, key: String) {
    val intent = Intent(key)
    intent.putExtra(Constant.GAUGE_UP, 1)
    context.sendBroadcast(intent)
}

fun getMessageIntent(intent: Intent, damage: DMGresult) {
    if (damage.hit && damage.crit) {
        intent.putExtra(Constant.SET_MESSAGE, "CRITICAL!\nHP-${damage.dmg}")
    } else if (damage.hit && damage.dmg > 0) {
        intent.putExtra(Constant.SET_MESSAGE, "HP-${damage.dmg}")
    } else if (damage.hit) {
        intent.putExtra(Constant.SET_MESSAGE, "Blocked")
    } else {
        intent.putExtra(Constant.SET_MESSAGE, "Missed")
    }
}

fun getDoubleAttackMessage(intent: Intent, damage1: DMGresult, damage2: DMGresult) {
    var message: String = if (damage1.hit && damage1.crit) {
        "CRITICAL!\nHP-${damage1.dmg}"
    } else if (damage1.hit && damage1.dmg > 0) {
        "HP-${damage1.dmg}"
    } else if (damage1.hit) {
        "Blocked"
    } else {
        "Missed"
    }
    message += if (damage2.hit && damage2.crit) {
        "\nCRITICAL!\nHP-${damage2.dmg}"
    } else if (damage2.hit && damage2.dmg > 0) {
        "\nHP-${damage2.dmg}"
    } else if (damage2.hit) {
        "\nBlocked"
    } else {
        "\nMissed"
    }
        intent.putExtra(Constant.SET_MESSAGE, message)
    }

fun getFormRequire(form: String): String {
    return "this ability require $form Form"
}

fun getSoundInFighterSelection(name:String,context: Context): MediaPlayer {
    return when (name) {
        Constant.RYUKI -> MediaPlayer.create(context,R.raw.ready_ryuki)
        Constant.FAIZ -> MediaPlayer.create(context,R.raw.ready_faiz)
        Constant.KABUTO -> MediaPlayer.create(context,R.raw.ready_kabuto)
        Constant.OOO -> MediaPlayer.create(context,R.raw.ready_ooo)
        Constant.GAIM -> MediaPlayer.create(context,R.raw.ready_gaim)
        else -> MediaPlayer.create(context,R.raw.ready_geats)
    }
}