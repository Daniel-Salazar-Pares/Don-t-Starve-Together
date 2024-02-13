package com.example.dontstarvetogether.api

import com.example.dontstarvetogether.model.Data
import com.example.dontstarvetogether.model.DataItem
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {
    @GET("characters")
    suspend fun getCharacters(): Response<Data>

    companion object {
        val BASE_URL = "https://dont-starve-together-api.xyz/api/survivors"
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}