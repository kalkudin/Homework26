package com.example.homework26.domain.usecase

import com.example.homework26.data.common.Resource
import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFilteredCategoryItemsUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {
    suspend operator fun invoke(filterKeyWord: String): Flow<Resource<List<CategoryItem>>> {
        return categoriesRepository.getCategoryItems().map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val filteredList = resource.data.flatMap { category ->
                        filterCategory(category, filterKeyWord)
                    }
                    Resource.Success(filteredList)
                }
                else -> resource
            }
        }
    }

    private fun filterCategory(category: CategoryItem, filterKeyWord: String): List<CategoryItem> {
        val matchesFilter = category.name.contains(filterKeyWord, ignoreCase = true) ||
                category.nameGerman.contains(filterKeyWord, ignoreCase = true) ||
                category.children.any { child ->
                    child.name.contains(filterKeyWord, ignoreCase = true) ||
                            child.nameGerman.contains(filterKeyWord, ignoreCase = true)
                }

        return if (matchesFilter) {
            listOf(
                category.copy(
                    children = category.children.flatMap { child ->
                        filterCategory(child, filterKeyWord)
                    }
                )
            )
        } else {
            category.children.flatMap { child ->
                filterCategory(child, filterKeyWord)
            }
        }
    }
}