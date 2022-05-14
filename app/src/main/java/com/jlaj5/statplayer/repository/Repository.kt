package com.jlaj5.statplayer.repository

import com.jlaj5.statplayer.api.RetrofitInstance
import com.jlaj5.statplayer.model.PostX
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<PostX> {
        return RetrofitInstance.api.getPost()
    }
}