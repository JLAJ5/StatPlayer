package com.jlaj5.statplayer.api

import com.jlaj5.statplayer.model.PostX
import retrofit2.Response
import retrofit2.http.GET

interface PlayerApi {

    @GET("teste.json")
    suspend fun getPost(): Response<PostX>
}