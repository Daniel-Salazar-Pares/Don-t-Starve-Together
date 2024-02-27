package com.example.dontstarvetogether.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.viewmodel.APIViewModel
import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*


@Composable
fun DetailScreenCharacters(navController: NavController, characterName: String, myViewModel: APIViewModel) {
    val characterDetails by myViewModel.characters.observeAsState(initial = Data())

    // A simple UI that shows character details or a loading indicator
    if (characterDetails.isNotEmpty()) {
        val character = characterDetails.find { it.name == characterName }
        if (character != null) {
            // Detail view for a character
            Column() {
                Text(
                    text = character.name,
                )
                Text(text = "Nickname: ${character.nickname}")
                Text(text = "Quote: \"${character.quote}\"")
            }
        } else {
            // In case the character is not found
            Text("Character details not found.")
        }
    } else {
        // Show a loading indicator until characters are fetched
        CircularProgressIndicator()
    }
}
