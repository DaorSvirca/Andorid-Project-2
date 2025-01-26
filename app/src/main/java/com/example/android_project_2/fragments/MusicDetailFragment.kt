package com.cacttus.navigationdrawergr_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cacttus.navigationdrawergr_2.viewModel.MusicDetailViewModel
import com.cacttus.navigationdrawergr_3.databinding.FragmentMusicDetailBinding

class MusicDetailFragment : Fragment() {

    private lateinit var viewModel: MusicDetailViewModel
    private lateinit var binding: FragmentMusicDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(MusicDetailViewModel::class.java)

        // Get the recipe ID from the arguments
        val musicId = arguments?.getInt("musicId") ?: 0
        viewModel.loadMusic(musicId)

        // Observe the 'recipe' LiveData and update UI when data is available
        viewModel.music.observe(viewLifecycleOwner) { music ->
            // Handle the case where recipe might be null
            if (music != null) {
                binding.musicTitle.text = music.title
                binding.musicBody.text = music.body
            } else {
                // Handle case where the recipe is not found or null
                binding.musicTitle.text = "Music not found"
            }
        }
    }
}
