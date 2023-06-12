package com.example.kamenriderdesiregrandfighter

import kotlin.random.Random

class RNG {
    fun getRandomRider():String {
        val playableCharacters = listOf(Constant.GEATS,Constant.OOO,Constant.GAIM,Constant.FAIZ,Constant.KABUTO,Constant.RYUKI)
        return playableCharacters.random()
    }
}