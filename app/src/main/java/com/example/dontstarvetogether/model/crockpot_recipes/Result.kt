package com.example.dontstarvetogether.model.crockpot_recipes

data class Result(
    val asset: String,
    val cookingTime: String,
    val isWarlySpecial: Boolean,
    val name: String,
    val sideEffect: String,
    val spoils: String,
    val stats: Stats,
    val type: String
)