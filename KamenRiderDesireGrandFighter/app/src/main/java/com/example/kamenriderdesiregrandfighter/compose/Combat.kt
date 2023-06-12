package com.example.kamenriderdesiregrandfighter.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.RNG
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

@Composable
fun CombatScreen(player1:String, player2: String, gameMode: String, navController: NavController) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Row {
            Fighter(player = player1, nameTag = if (gameMode == Constant.SINGLE_PLAYER) "Player" else "Player 1")
            Fighter(player = if (player2 != Constant.UNSELECTED) player2 else RNG().getRandomRider(), if (gameMode == Constant.SINGLE_PLAYER) "CPU" else "Player 2")
        }

        BackHandler(enabled = true) {
            navController.navigate(route = Screen.Main.route)
        }
    }
}

@Composable
fun Fighter(player:String, nameTag: String) {
    var riderImage = rememberSaveable {
        mutableStateOf(getImageIDFromName(player))
    }

    Column {
        Text(text = nameTag)
        Text(text = "Kamen Rider $player")
        Image(painter = painterResource(
            id = riderImage.value),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(175.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FighterPreview() {
    KamenRiderDesireGrandFighterTheme() {
        Fighter(player = Constant.GEATS, nameTag = "Player 1")
    }
}