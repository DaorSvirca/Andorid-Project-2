package com.example.android_project_2.api

import android.telecom.Call
import com.example.android_project_2.model.MusicResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApiService {

    @GET("musics")
    fun getMusics(@Query("limit") limit: Int) : retrofit2.Call<MusicResponse>
}