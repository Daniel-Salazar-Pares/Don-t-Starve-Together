package com.example.dontstarvetogether.api

class Repository {
    val apiInterface = APIInterface.create()

    suspend fun getAllCharacters() = apiInterface.getCharacters()

}