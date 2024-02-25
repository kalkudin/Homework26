package com.example.homework26.presentation.util

import android.util.Log
import com.example.homework26.data.common.Resource
import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.presentation.state.CategoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun <T> handleResource(resource: Resource<T>, flow: MutableStateFlow<CategoryState>) {
    when (resource) {
        is Resource.Success -> {
            val categories = resource.data.let { domainItems ->
                @Suppress("UNCHECKED_CAST")
                if (domainItems is List<*>) {
                    (domainItems as List<CategoryItem>).flatMap { it.flatten() }
                } else {
                    emptyList()
                }
            }

            categories.forEach { category ->
                Log.d("HomeViewModel", "Category ID: ${category.id}, Name: ${category.name}, Children: ${category.numberOfChildren}")
            }

            flow.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    categories = categories,
                    errorMessage = null
                )
            }
        }
        is Resource.Error -> {
            Log.e("HomeViewModel", "Error: ${resource.errorType}")
            flow.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    errorMessage = getErrorMessage(resource.errorType)
                )
            }
        }
        is Resource.Loading -> {
            Log.d("HomeViewModel", "Loading...")
            flow.update { currentState ->
                currentState.copy(isLoading = true)
            }
        }
    }
}