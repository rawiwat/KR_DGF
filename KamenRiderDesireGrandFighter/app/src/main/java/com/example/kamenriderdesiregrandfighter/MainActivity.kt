package com.example.kamenriderdesiregrandfighter

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
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

    @SuppressLint("SourceLockedOrientationActivity")
    //this is not a mistake locking screen to portrait is intentional
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            KamenRiderDesireGrandFighterTheme {
                navController = rememberNavController()
                Navigator(navController = navController as NavHostController)
            }
        }
    }

}
