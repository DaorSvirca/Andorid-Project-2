package com.cacttus.navigationdrawergr_2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_project_2.MusicRepository

class FavoritesViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseViewModelFactory {
            FavoritesViewModel(MusicRepository.getInstance())
        }.create(modelClass)
    }
}