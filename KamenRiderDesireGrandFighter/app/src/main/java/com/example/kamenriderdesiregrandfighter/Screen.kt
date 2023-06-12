package com.example.kamenriderdesiregrandfighter

sealed class Screen(val route: String) {
    object Main: Screen(route = Constant.MAIN_MENU)
    object Selection: Screen(route = "${Constant.SELECTION_MENU}/{${Constant.GAME_MODE}}") {
        fun passGameMode(gameMode: String): String {
            return this.route.replace(oldValue = "{${Constant.GAME_MODE}}", newValue = gameMode)
        }
    }
    object Combat: Screen(route = "${Constant.COMBAT}/{${Constant.PLAYER_ONE}}/{${Constant.PLAYER_TWO}}/{${Constant.GAME_MODE}}") {
        fun passCombatSetting(player1:String, player2: String, gameMode: String): String {
            return "${Constant.COMBAT}/$player1/$player2/$gameMode"
        }
    }
}