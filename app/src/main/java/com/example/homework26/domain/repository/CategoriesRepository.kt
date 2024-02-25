package com.example.homework26.domain.repository

import com.example.homework26.data.common.Resource
import com.example.homework26.domain.model.CategoryItem
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository{
    suspend fun getCategoryItems() : Flow<Resource<List<CategoryItem>>>
}