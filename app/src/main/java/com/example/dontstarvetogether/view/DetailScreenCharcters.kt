package com.example.dontstarvetogether.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.viewmodel.APIViewModel
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreenCharacters(navController: NavController, characterName: String, myViewModel: APIViewModel) {
    val characterDetails by myViewModel.characters.observeAsState(initial = Data())

    // A simple UI that shows character details or a loading indicator
    if (characterDetails.isNotEmpty()) {
        val character = characterDetails.find { it.name == characterName }
        if (character != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.name,
                )

                GlideImage(
                    model = character.portrait,
                    contentDescription = "Character Portrait",
                    contentScale = ContentScale.Crop,
                    modifier = androidx.compose.ui.Modifier.size(100.dp)
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
