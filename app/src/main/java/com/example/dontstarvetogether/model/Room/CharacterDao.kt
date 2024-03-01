package com.example.dontstarvetogether.model.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters():MutableList<Character>

    @Query("SELECT * FROM CharacterEntity WHERE id = :characterId")
    suspend fun getCharacterById(characterId:Int):MutableList<Character>

    @Insert
    suspend fun addCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

}