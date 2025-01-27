package com.cacttus.navigationdrawergr_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacttus.navigationdrawergr_2.viewModel.BaseViewModelFactory
import com.cacttus.navigationdrawergr_2.viewModel.MusicListViewModel
import com.cacttus.navigationdrawergr_3.databinding.FragmentMusicListBinding
import com.example.android_project_2.MusicRepository
import com.example.android_project_2.adapters.MusicAdapter

class MusicListFragment : Fragment() {
    private lateinit var viewModel: MusicListViewModel
    private lateinit var binding: FragmentMusicListBinding
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel with non-abstract factory
        val factory = BaseViewModelFactory {
            MusicListViewModel(MusicRepository.getInstance())
        }
        viewModel = ViewModelProvider(this, factory).get(MusicListViewModel::class.java)

        adapter = MusicAdapter(emptyList()) { music ->
            // Handle recipe click
        }

        binding.musicRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.musicRecyclerView.adapter = adapter

        viewModel.musics.observe(viewLifecycleOwner) { musics ->
            adapter.updateMusics(musics)
        }

        viewModel.loadMusics()
    }
}