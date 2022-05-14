package com.jlaj5.statplayer.api

import com.jlaj5.statplayer.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PlayerApi by lazy {
        retrofit.create(PlayerApi::class.java)
    }
}