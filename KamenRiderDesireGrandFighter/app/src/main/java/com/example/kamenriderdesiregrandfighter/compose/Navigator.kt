package com.example.kamenriderdesiregrandfighter.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.Screen

@Composable
fun Navigator(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen( navController = navController )
        }
        composable(
            route = Screen.Selection.route,
            arguments = listOf(navArgument(Constant.GAME_MODE) {
                type = NavType.StringType
            })
        ) {
            FighterSelectionScreen(gameMode = it.arguments?.getString(Constant.GAME_MODE).toString(), navController = navController)
        }

        composable(
            route = Screen.Combat.route,
            arguments = listOf(
                navArgument(Constant.PLAYER_ONE) {
                    type = NavType.StringType },
                navArgument(Constant.PLAYER_TWO) {
                    type = NavType.StringType },
                navArgument(Constant.GAME_MODE) {
                    type = NavType.StringType }
            )
        ) {
            CombatScreen(
                player1 = it.arguments?.getString(Constant.PLAYER_ONE).toString(),
                player2 = it.arguments?.getString(Constant.PLAYER_TWO).toString(),
                gameMode = it.arguments?.getString(Constant.GAME_MODE).toString(),
                navController = navController)
        }
    }
}
