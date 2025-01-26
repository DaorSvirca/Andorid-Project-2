package com.cacttus.navigationdrawergr_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_project_2.MusicRepository
import com.example.android_project_2.model.Music

class FavoritesViewModel(private val repository: MusicRepository) : ViewModel() {
    private val _favoriteMusics = MutableLiveData<List<Music>>()
    val favoriteMusics: LiveData<List<Music>> get() = _favoriteMusics

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        _favoriteMusics.value = repository.getFavoriteMusics()
    }
}