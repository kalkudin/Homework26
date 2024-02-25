package com.example.homework26.presentation.state

import com.example.homework26.presentation.model.Categories

data class CategoryState(
    val categories : List<Categories>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)