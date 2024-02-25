package com.example.homework26.data.repository

import android.util.Log
import com.example.homework26.data.common.HandleResponse
import com.example.homework26.data.common.Resource
import com.example.homework26.data.remote.mapper.mapToDomain
import com.example.homework26.data.remote.mapper.toDomain
import com.example.homework26.data.remote.service.CategoriesService
import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val categoriesService: CategoriesService
) : CategoriesRepository {
    override suspend fun getCategoryItems(): Flow<Resource<List<CategoryItem>>> {
        return handleResponse.safeApiCall { categoriesService.getCategories() }.mapToDomain {
            categoriesList -> categoriesList.map { it.toDomain() }
        }
    }
}