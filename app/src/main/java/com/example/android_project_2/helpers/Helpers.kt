package com.example.android_project_2.helpers

import android.content.Context
import android.content.SharedPreferences
import com.example.android_project_2.api.MusicApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Provider.Service

object Helpers {
        private const val BASE_URL = "https://dummyjson.com/"

        val api : MusicApiService by lazy {
                Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(MusicApiService::class.java)
        }


        fun provideSharedPreferences(context: Context): SharedPreferences {
                return context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        }

        fun addIntToSharedPreferences(context: Context, key: String, value: Int) {
                provideSharedPreferences(context).edit().putInt(key, value).apply()
        }

        fun getIntFromSharedPreferences(context: Context, key: String): Int {
                return provideSharedPreferences(context).getInt(key, 0)
        }

}