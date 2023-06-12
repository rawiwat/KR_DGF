package com.example.kamenriderdesiregrandfighter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kamenriderdesiregrandfighter.compose.Navigator
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KamenRiderDesireGrandFighterTheme {
                navController = rememberNavController()
                Navigator(navController = navController as NavHostController)
            }
        }
    }

}
