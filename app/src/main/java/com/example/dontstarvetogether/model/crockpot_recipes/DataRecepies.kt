package com.example.dontstarvetogether.model.crockpot_recipes

data class DataRecepies(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int
)