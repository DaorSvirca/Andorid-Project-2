package com.example.android_project_2

import com.example.android_project_2.model.Music

interface MusicDataSource {
    fun getMusics(): List<Music>
    fun searchRecepies(query: String): List<Music>
    fun getFavoritesRecepies() : List<Music>
}