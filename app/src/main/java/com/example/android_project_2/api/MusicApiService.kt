package com.example.android_project_2.api


import com.example.android_project_2.model.MusicResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApiService {

    @GET("posts")
    fun getMusics(@Query("limit") limit: Int) : Call<MusicResponse>
}