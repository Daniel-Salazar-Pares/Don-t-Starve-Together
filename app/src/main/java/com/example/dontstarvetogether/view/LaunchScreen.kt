package com.example.dontstarvetogether.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dontstarvetogether.R
import com.example.dontstarvetogether.Routes

import kotlinx.coroutines.delay
import java.time.format.TextStyle

@Composable
fun Splash(alphaAnim: Float) {
    Box(modifier = Modifier.fillMaxSize()) {
        /*
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

         */
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo", alpha = alphaAnim
        )
        val scaryFontFamily = FontFamily(Font(R.font.deathrattlebb_reg, FontWeight.SemiBold))

        Text(
            text = "Don't Starve Together",
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            fontFamily = scaryFontFamily,
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = scaryFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )
        )

    }
}

@Composable
fun LaunchScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.popBackStack()
            navController.navigate(Routes.ScrollScreen.route)
    }
    Splash(alphaAnim.value)
}