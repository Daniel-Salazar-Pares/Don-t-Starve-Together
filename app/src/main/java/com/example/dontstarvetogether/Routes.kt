package com.example.dontstarvetogether

sealed class Routes (val route: String) {
    object LaunchScreen : Routes("launchScreen")
    object ScrollScreen : Routes("ScrollScreen")
    object DetailScreenCharacters : Routes("DetailScreenCharacters/{characterName}") {
        fun createRoute(characterName: String) = "DetailScreenCharacters/$characterName"
    }

}