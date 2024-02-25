package com.example.homework26.domain.usecase

import com.example.homework26.data.common.Resource
import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryItemsUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke() : Flow<Resource<List<CategoryItem>>> {
        return categoriesRepository.getCategoryItems()
    }
}