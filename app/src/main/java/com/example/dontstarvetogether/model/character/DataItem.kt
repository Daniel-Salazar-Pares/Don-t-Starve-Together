package com.example.dontstarvetogether.model.character

data class DataItem(
    val animatedShort: String,
    val backstory: Backstory,
    val bigportrait: String,
    val birthdate: String,
    val description: String,
    val entersTheConstantWith: List<Any>,
    val favoriteFood: String,
    val name: String,
    val nickname: String,
    val perks: List<String>,
    val portrait: String,
    val quote: String,
    val stats: Stats
)