package com.example.kamenriderdesiregrandfighter.Model

fun genericMoveSet(): List<Unit> {

    val moveSet = mutableListOf<Unit>()

    fun attack(attacker:KamenRider, defender: KamenRider) {

}
    return moveSet
}

sealed class KamenRider (var name: String,
                  var form: String,
                  var health: Int,
                  var energy: Int,
                  var attack:Int,
                  var defense: Int,
                  var speed: Int,
                  var moveSet: List<Unit> = genericMoveSet()
) {

}

