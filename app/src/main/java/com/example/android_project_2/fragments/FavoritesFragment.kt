package com.cacttus.navigationdrawergr_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacttus.navigationdrawergr_2.viewModel.FavoritesViewModel
import com.cacttus.navigationdrawergr_2.viewModel.FavoritesViewModelFactory
import com.cacttus.navigationdrawergr_3.databinding.FragmentFavoritesBinding
import com.example.android_project_2.adapters.MusicAdapter

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel with factory
        val factory = FavoritesViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(FavoritesViewModel::class.java)

        adapter = MusicAdapter(emptyList()) { music ->
            // Handle recipe click (navigate to detail)
        }

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = adapter

        viewModel.favoriteMusics.observe(viewLifecycleOwner) { musics ->
            adapter.updateMusics(musics)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}