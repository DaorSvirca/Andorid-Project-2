package com.cacttus.navigationdrawergr_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacttus.navigationdrawergr_2.viewModel.BaseViewModelFactory
import com.cacttus.navigationdrawergr_2.viewModel.SearchViewModel
import com.cacttus.navigationdrawergr_3.databinding.FragmentSearchBinding
import com.example.android_project_2.MusicRepository
import com.example.android_project_2.adapters.MusicAdapter

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel with factory
        val factory = BaseViewModelFactory {
            SearchViewModel(MusicRepository.getInstance())
        }
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)

        adapter = MusicAdapter(emptyList()) { music ->
            // Handle recipe click (navigate to detail)
        }

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.searchRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { musics ->
            if (musics.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.searchRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                adapter.updateMusics(musics)
            }
        }

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchMusics(query)
            }
        }
    }
}