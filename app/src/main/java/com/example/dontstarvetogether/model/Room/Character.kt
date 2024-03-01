package com.example.dontstarvetogether.model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dontstarvetogether.model.character.Backstory
import com.example.dontstarvetogether.model.character.Stats

@Entity(tableName = "CharacterEntity")
data class Character (
    @PrimaryKey (autoGenerate = true) val id : Int,
    val animatedShort: String,
    val bigportrait: String,
    val birthdate: String,
    val description: String,
    val favoriteFood: String,
    val name: String,
    val nickname: String,
    val portrait: String,
    val quote: String,
)