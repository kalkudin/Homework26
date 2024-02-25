package com.example.homework26.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework26.data.common.Resource
import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.domain.usecase.GetCategoryItemsUseCase
import com.example.homework26.domain.usecase.GetFilteredCategoryItemsUseCase
import com.example.homework26.presentation.event.HomeEvent
import com.example.homework26.presentation.state.CategoryState
import com.example.homework26.presentation.util.flatten
import com.example.homework26.presentation.util.getErrorMessage
import com.example.homework26.presentation.util.handleResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryItemsUseCase: GetCategoryItemsUseCase,
    private val getFilteredCategoryItemsUseCase: GetFilteredCategoryItemsUseCase
) : ViewModel() {

    private val _categoriesFlow = MutableStateFlow(CategoryState())
    val categoriesFlow: StateFlow<CategoryState> = _categoriesFlow.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCategories -> getCategories()
            is HomeEvent.GetFilteredCategories -> getFilteredCategories(event.filterKeyWord)
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryItemsUseCase().collect { resource ->
                handleResource(resource, _categoriesFlow)
            }
        }
    }

    private fun getFilteredCategories(filterKeyWord: String) {
        viewModelScope.launch {
            getFilteredCategoryItemsUseCase(filterKeyWord = filterKeyWord).collect { resource ->
                handleResource(resource, _categoriesFlow)
            }
        }
    }
}