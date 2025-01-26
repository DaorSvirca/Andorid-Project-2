package com.cacttus.navigationdrawergr_2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_project_2.MusicRepository
import com.example.android_project_2.model.Music

class SearchViewModel(private val repository: MusicRepository) : ViewModel() {
    private val _searchResults = MutableLiveData<List<Music>>()
    val searchResults: LiveData<List<Music>> get() = _searchResults

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun searchMusics(query: String) {
        _loading.value = true
        _searchResults.value = repository.searchMusics(query)
        _loading.value = false
    }
}