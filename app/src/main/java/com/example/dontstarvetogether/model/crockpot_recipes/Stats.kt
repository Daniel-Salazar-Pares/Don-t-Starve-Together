package com.example.dontstarvetogether.model.crockpot_recipes

data class Stats(
    val health: Int,
    val hunger: Int,
    val sanity: Int
) {
    operator fun iterator(): Iterator<String> {
        return listOf("Health: $health", "Hunger: $hunger", "Sanity: $sanity").iterator()
    }
}