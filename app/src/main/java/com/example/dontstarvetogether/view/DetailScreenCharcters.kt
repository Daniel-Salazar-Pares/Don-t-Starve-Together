package com.example.dontstarvetogether.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.BasicText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.model.character.DataItem


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreenCharacters(navController: NavController, characterName: String, myViewModel: APIViewModel) {
    val characterDetails by myViewModel.characters.observeAsState(initial = Data())

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
                    contentDescription = "Charac ter Portrait",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
                CustomText(character)
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

@Composable
fun CustomText(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Nickname: ")
        }
        append(character.nickname)
    }

    Text(text = text)
}