package com.example.homework26.presentation.home

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework26.databinding.FragmentHomeLayoutBinding
import com.example.homework26.presentation.adapter.CategoriesRecyclerAdapter
import com.example.homework26.presentation.base.BaseFragment
import com.example.homework26.presentation.event.HomeEvent
import com.example.homework26.presentation.state.CategoryState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding>(FragmentHomeLayoutBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter

    override fun bind() {
        bindCategories()
        bindCategoriesRecyclerView()
    }

    override fun bindViewActionListeners() {
        bindFilterSearch()
    }

    override fun bindObservers() {
        bindCategoriesFlow()
    }

    private fun bindCategories() {
        homeViewModel.onEvent(HomeEvent.GetCategories)
    }

    private fun bindCategoriesRecyclerView() {
        categoriesAdapter = CategoriesRecyclerAdapter()
        binding.rvCategoryItems.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun bindFilterSearch() {
        var searchJob: Job? = null
        binding.etFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(500)
                    val searchText = s.toString()
                    if (searchText.isNotEmpty()) {
                        homeViewModel.onEvent(HomeEvent.GetFilteredCategories(searchText))
                    } else {
                        homeViewModel.onEvent(HomeEvent.GetCategories)
                    }
                }
            }
        })
    }

    private fun bindCategoriesFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.categoriesFlow.collect { state ->
                    handleState(state)
                }
            }
        }
    }

    private fun handleState(state: CategoryState) {
        with(binding) {
            progressBar.isVisible = state.isLoading

            state.errorMessage?.let {
                showError(errorMessage = state.errorMessage)
                progressBar.isVisible = false
            }

            state.categories?.let { categoriesAdapter.submitList(it)
                progressBar.isVisible = false}
        }
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).setAction("OK") {}.show()
    }
}