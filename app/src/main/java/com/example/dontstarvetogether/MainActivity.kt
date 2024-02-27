package com.example.dontstarvetogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dontstarvetogether.ui.theme.DontStarveTogetherTheme
import com.example.dontstarvetogether.view.*
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
        val api = APIViewModel()
        NavHost(
            navController = navigationController,
            startDestination = Routes.LaunchScreen.route
        ) {
            composable(Routes.LaunchScreen.route) { LaunchScreen(navigationController) }
            composable(Routes.ScrollScreen.route) { ScrollScreen(api ,navigationController) }
            composable(
                Routes.DetailScreenCharacters.route,
                arguments = listOf(navArgument("characterName") { type = NavType.StringType })
            ) { backStackEntry ->
                val characterName = backStackEntry.arguments?.getString("characterName")
                if (characterName != null) {
                    DetailScreenCharacters(navigationController, characterName, api)
                } else {
                    Text("Character not found")
                }
            }
        }
    }

}
/*
@Composable
fun DetailScreenCharacters(navController: NavController, characterName: String) {


}

 */


