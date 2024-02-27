package com.example.dontstarvetogether.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.R
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.model.character.DataItem
import com.example.dontstarvetogether.viewmodel.APIViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreenCharacters(
    navController: NavController,
    characterName: String,
    myViewModel: APIViewModel
) {
    val characterDetails by myViewModel.characters.observeAsState(initial = Data())
    val scaryFontFamily = FontFamily(Font(R.font.deathrattlebb_reg, FontWeight.Bold))
    if (characterDetails.isNotEmpty()) {
        val character = characterDetails.find { it.name == characterName }
        if (character != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.name,
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = scaryFontFamily,
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = scaryFontFamily,
                    )
                )

                GlideImage(
                    model = character.portrait,
                    contentDescription = "Character Portrait",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
                Column (
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Nickname(character)
                    Quote(character)
                    Description(character)
                    Birthdate(character)
                    FavoriteFood(character)
                }
                Stats(character)
                Box(

                ) {
                    var show by remember { mutableStateOf(false) }
                    Button(onClick = { show = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),)

                    {
                        Text(text = "Backstory")
                    }
                    MyDialog(show, { show = false }, character)
                }
            }
        } else {
            Text("Character details not found.")
        }
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun Nickname(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Nickname: ")
        }
        append(character.nickname)
    }
    Text(text = text)
}

@Composable
fun Quote(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Quote: ")
        }
        append(character.quote)
    }

    Text(text = text)
}

@Composable
fun Description(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Description: ")
        }
        append(character.description)
    }

    Text(text = text)
}

@Composable
fun Birthdate(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Birthdate: ")
        }
        append(character.birthdate)
    }

    Text(text = text)
}

@Composable
fun FavoriteFood(character: DataItem) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Favorite Food: ")
        }
        append(character.favoriteFood)
    }

    Text(text = text)
}

@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, character: DataItem) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                Text(
                    text = character.backstory.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.weight(1f)) { // Box to fill the space
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(
                            text = character.backstory.body,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Stats (character: DataItem) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .border(width = 2.dp, color = Color.Black)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Stats: ", fontWeight = FontWeight.Bold)
            Column {
                for (stat in character.stats) {
                    Text(text = stat)
                }
            }
        }
    }
}