package com.example.dontstarvetogether.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.R
import com.example.dontstarvetogether.model.crockpot_recipes.DataRecepies
import com.example.dontstarvetogether.viewmodel.APIViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreenRecepies(
    navController: NavController,
    recepieName: String,
    myViewModel: APIViewModel,
) {
    val recepieDetails by myViewModel.recepies.observeAsState(
        initial = DataRecepies(
            0,
            emptyList(),
            0
        )
    )
    val scaryFontFamily = FontFamily(Font(R.font.deathrattlebb_reg, FontWeight.Bold))
    if (recepieDetails.results.isNotEmpty()) {
        val recepie = recepieDetails.findByName(recepieName)
        if (recepie != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = recepieName,
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = scaryFontFamily,
                    style = TextStyle(
                        fontFamily = scaryFontFamily,
                    )
                )
                GlideImage(
                    model = recepie.asset,
                    contentDescription = "Recepie image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                    )
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){


                    Box(
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

                            Column {
                                Text("Stats: ", fontWeight = FontWeight.Bold)
                                for (stat in recepie.stats) {
                                    Text(text = stat)
                                }
                            }
                        }
                    }
                    Column {
                        Type(recepie.type)
                        Spoil(recepie.spoils)
                        CookTime(recepie.cookingTime)
                        WarlySpecial(recepie.isWarlySpecial)

                    }
                }
                SideEfect(recepie.sideEffect)
            }
        }
    }
}

@Composable
fun Type(type: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Type: ")
        }
        append(type)
    }

    Text(text = text)
}

@Composable
fun Spoil (spoils: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Spoil Time: ")
        }
        append(spoils)
    }

    Text(text = text)
}

@Composable
fun CookTime (cookingTime: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Cook Time: ")
        }
        append(cookingTime)
    }

    Text(text = text)
}

@Composable
fun WarlySpecial (isWarlySpecial: Boolean) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Warly Special: ")
        }
        append(isWarlySpecial.toString())
    }

    Text(text = text)
}

@Composable
fun SideEfect (sideEffect: String) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Side Effect: ")
        }
        append(sideEffect)
    }

    Text(text = text)
}