package com.example.kamenriderdesiregrandfighter.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kamenriderdesiregrandfighter.Constant
import com.example.kamenriderdesiregrandfighter.MainActivity
import com.example.kamenriderdesiregrandfighter.R
import com.example.kamenriderdesiregrandfighter.Screen
import com.example.kamenriderdesiregrandfighter.ui.theme.KamenRiderDesireGrandFighterTheme

@Composable
fun MainScreen(
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.krdgf_main_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SinglePlayerButton { navController.navigate(route = Screen.Selection.passGameMode(Constant.SINGLE_PLAYER)) }
            MultiPlayerButton { navController.navigate(route = Screen.Selection.passGameMode(Constant.MULTI_PLAYER)) }
        }
    }
}

@Composable
fun SinglePlayerButton(onClick: () -> Unit) {
    Button(onClick = onClick , modifier = Modifier.padding(10.dp)) {
        Text(text = "SinglePlayer")
    }
}

@Composable
fun MultiPlayerButton(onClick:()-> Unit = {}) {
    Button(onClick = onClick, modifier = Modifier.padding(10.dp)) {
        Text(text = "MultiPlayer")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    KamenRiderDesireGrandFighterTheme {
        MainScreen(navController = NavController(context = MainActivity()))
    }
}

