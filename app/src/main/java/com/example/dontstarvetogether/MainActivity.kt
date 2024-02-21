package com.example.dontstarvetogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.model.character.Backstory
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.model.character.DataItem
import com.example.dontstarvetogether.model.character.Stats
import com.example.dontstarvetogether.ui.theme.DontStarveTogetherTheme
import com.example.dontstarvetogether.view.LaunchScreen
import com.example.dontstarvetogether.view.ScrollScreen
import com.example.dontstarvetogether.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<APIViewModel>()
        setContent {
            DontStarveTogetherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DontStarveNavHost()
                }
            }
        }
    }
    @Composable
    fun DontStarveNavHost() {
        val navigationController = rememberNavController()
        NavHost(
            navController = navigationController,
            startDestination = Routes.LaunchScreen.route
        ) {
            composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
            composable(Routes.ScrollScreen.route) { ScrollScreen(APIViewModel, navigationController) }
        }
    }

}


