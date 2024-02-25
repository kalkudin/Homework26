package com.example.homework26.presentation.util

import com.example.homework26.domain.model.CategoryItem
import com.example.homework26.presentation.model.Categories

fun CategoryItem.flatten(): List<Categories> {
    val flattenedList = mutableListOf(
        Categories(
            id = id,
            name = name,
            nameGerman = nameGerman,
            numberOfChildren = children.size
        )
    )

    children.forEach { child ->
        flattenedList.addAll(child.flatten())
    }

    return flattenedList
}