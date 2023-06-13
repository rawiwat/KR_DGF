package com.example.kamenriderdesiregrandfighter.Model

open class Move (val name: String, val type: String) {
    open fun function(user:KamenRider, opponent:KamenRider) {
    }
}
fun genericMoveSet(): List<Move> {

    val moveSet = mutableListOf<Move>()

    class attack: Move(name = "attack", type = "offense") {
        override fun function(user: KamenRider, opponent: KamenRider) {
            opponent.health -= user.attack
        }
    }
    moveSet.add(attack())
    return moveSet
}

open class KamenRider (var name: String,
                       var form: String,
                       var health: Int,
                       var energy: Int,
                       var attack:Int,
                       var defense: Int,
                       var speed: Int,
                       var moveSet: List<Move> = genericMoveSet()
) {

}

