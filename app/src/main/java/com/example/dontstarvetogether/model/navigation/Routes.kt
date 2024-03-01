package com.example.dontstarvetogether.model.navigation

sealed class Routes (val route: String) {
    object LaunchScreen : Routes("launchScreen")
    object ScrollScreen : Routes("ScrollScreen")

    object DetailScreenCharacters : Routes("DetailScreenCharacters/{characterName}") {
        fun createRoute(characterName: String) = "DetailScreenCharacters/$characterName"
    }
    object DetailScreenRecepies : Routes("DetailScreenRecepies/{recepieName}") {
        fun createRoute(recepieName: String) = "DetailScreenRecepies/$recepieName"
    }
}