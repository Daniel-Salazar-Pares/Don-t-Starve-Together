package com.example.dontstarvetogether.model.Room

import android.app.Application
import androidx.room.Room

class CharacterApplication: Application() {
    companion object {
        lateinit var characterDatabase: CharacterDatabase
    }
    override fun onCreate() {
        super.onCreate()
        characterDatabase = Room.databaseBuilder(this,
            CharacterDatabase::class.java,
            "CharacterDatabase").build()
    }
}