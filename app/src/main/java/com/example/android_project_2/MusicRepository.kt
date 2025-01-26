package com.example.android_project_2

import com.example.android_project_2.model.Music

class MusicRepository private constructor() {
    private var _musics = mutableListOf<Music>()
    val musics: List<Music> get() = _musics
    private val favoriteIds = mutableSetOf<Int>()

    // Add this method to update musics
    fun updateMusics(newMusics: List<Music>) {
        _musics.clear()
        _musics.addAll(newMusics)
    }

    fun addFavorite(musicId: Int) {
        favoriteIds.add(musicId)
    }

    fun removeFavorite(musicId: Int) {
        favoriteIds.remove(musicId)
    }

    fun getFavoriteMusics(): List<Music> {
        return _musics.filter { it.id in favoriteIds }
    }

    fun getMusicById(musicId: Int): Music? {
        return _musics.find { it.id == musicId }
    }

    fun searchMusics(query: String): List<Music> {
        val lowerCaseQuery = query.lowercase()
        return _musics.filter {
            it.title.lowercase().contains(lowerCaseQuery) ||
                    it.body.any { artist ->
                        artist.lowercase().contains(lowerCaseQuery)
                    }
        }
    }

    companion object {
        @Volatile
        private var instance: MusicRepository? = null

        fun getInstance(): MusicRepository {
            return instance ?: synchronized(this) {
                instance ?: MusicRepository().also { instance = it }
            }
        }
    }
}