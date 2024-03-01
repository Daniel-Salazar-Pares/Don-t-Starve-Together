package com.example.dontstarvetogether.api

import com.example.dontstarvetogether.model.Room.CharacterApplication
import com.example.dontstarvetogether.model.Room.Character

class Repository {
    val apiInterface = APIInterface.create()
    val daoInterfase = CharacterApplication.characterDatabase.characterDao()

    suspend fun getAllCharacters() = apiInterface.getCharacters()
    suspend fun getAllRecepies() = apiInterface.getRecepies()

    // Database

    suspend fun saveAsFavorite(character: Character) = daoInterfase.addCharacter(character)
    suspend fun deleteFavorite(character: Character) = daoInterfase.deleteCharacter(character)
    suspend fun isFavorite(character: Character) = daoInterfase.getCharacterById(character.id).isNotEmpty()
    suspend fun getFavoriteCharacters() = daoInterfase.getAllCharacters()

}