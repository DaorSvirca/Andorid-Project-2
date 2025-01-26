package com.example.android_project_2.api

import android.telecom.Call
import com.example.android_project_2.model.MusicResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApiService {

    @GET("posts?limit=10")
    fun getMusics(@Query("limit") limit: Int) : retrofit2.Call<MusicResponse>
}