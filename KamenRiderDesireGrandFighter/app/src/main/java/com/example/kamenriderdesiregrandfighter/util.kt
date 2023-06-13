package com.example.kamenriderdesiregrandfighter

import com.example.kamenriderdesiregrandfighter.Model.Faiz
import com.example.kamenriderdesiregrandfighter.Model.Gaim
import com.example.kamenriderdesiregrandfighter.Model.Geats
import com.example.kamenriderdesiregrandfighter.Model.Kabuto
import com.example.kamenriderdesiregrandfighter.Model.KamenRider
import com.example.kamenriderdesiregrandfighter.Model.OOO
import com.example.kamenriderdesiregrandfighter.Model.Ryuki

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

fun getFormName(kamenRider: KamenRider): String {
    var formName = ""
    if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.BASE_FORM) {
        formName = "Magnum Boost"
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.UPGRADE_FORM) {
        formName = "Command"
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.SUPER_FORM) {
        formName = "Laser Boost"
    } else if (kamenRider.name == Constant.GEATS && kamenRider.form == Constant.FINAL_FORM) {
        formName = "IX"
    } else if (kamenRider.name == Constant.RYUKI && kamenRider.form == Constant.FINAL_FORM) {
        formName = "Survive"
    } else if (kamenRider.name == Constant.FAIZ && kamenRider.form == Constant.SUPER_FORM) {
        formName = "Axel"
    } else if (kamenRider.name == Constant.FAIZ && kamenRider.form == Constant.FINAL_FORM) {
        formName = "Blaster"
    } else if (kamenRider.name == Constant.KABUTO && kamenRider.form == Constant.UPGRADE_FORM) {
        formName = "Masked"
    } else if (kamenRider.name == Constant.KABUTO && kamenRider.form == Constant.FINAL_FORM) {
        formName = "Hyper"
    } else if (kamenRider.name == Constant.GAIM && kamenRider.form == Constant.BASE_FORM) {
        formName = "Orange"
    } else if (kamenRider.name == Constant.GAIM && kamenRider.form == Constant.UPGRADE_FORM) {
        formName = "Jimbra Lemon"
    } else if (kamenRider.name == Constant.GAIM && kamenRider.form == Constant.SUPER_FORM) {
        formName = "Kachidoki"
    } else if (kamenRider.name == Constant.GAIM && kamenRider.form == Constant.FINAL_FORM) {
        formName = "Kiwami"
    } else if (kamenRider.name == Constant.OOO && kamenRider.form == Constant.BASE_FORM) {
        formName = "Tatoba"
    } else if (kamenRider.name == Constant.OOO && kamenRider.form == Constant.SUPER_FORM) {
        formName = "Tajador"
    } else if (kamenRider.name == Constant.OOO && kamenRider.form == Constant.FINAL_FORM) {
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
    val result = (current.toFloat() / max.toFloat())
    println("result = $result")
    return result
}