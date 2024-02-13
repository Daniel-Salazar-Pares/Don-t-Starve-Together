package com.example.dontstarvetogether.View

sealed class Routes (val route: String) {
    object LaunchScreen : Routes("launchScreen")
    object CharacterScreen : Routes("CharacterScreen")

}