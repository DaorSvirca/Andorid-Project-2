package com.cacttus.navigationdrawergr_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_project_2.MusicRepository
import com.example.android_project_2.model.Music

class MusicListViewModel(private val repository: MusicRepository) : ViewModel() {
    private val _musics = MutableLiveData<List<Music>>()
    val musics: LiveData<List<Music>> get() = _musics

    fun loadMusics() {
        _musics.postValue(repository.musics)
    }
}