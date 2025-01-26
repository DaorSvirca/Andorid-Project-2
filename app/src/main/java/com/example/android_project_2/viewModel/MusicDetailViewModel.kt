package com.cacttus.navigationdrawergr_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.android_project_2.MusicRepository
import com.example.android_project_2.model.Music

class MusicDetailViewModel(private val repository: MusicRepository) : ViewModel() {
    private val _music = MutableLiveData<Music?>()
    val music: LiveData<Music?> get() = _music

    fun loadMusic(recipeId: Int) {
        _music.value = repository.getMusicById(recipeId)
    }
}