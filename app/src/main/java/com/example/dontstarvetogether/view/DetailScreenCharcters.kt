package com.example.dontstarvetogether.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreenCharacters(navController: NavController, characterName: String) {
    val character = getCharacterList().find { it.name == characterName }
    if (character != null) {
        Column {

        }
    }

}
