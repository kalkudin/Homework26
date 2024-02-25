package com.example.homework26.data.remote.mapper

import com.example.homework26.data.remote.model.CategoryDto
import com.example.homework26.domain.model.CategoryItem

fun CategoryDto.toDomain() : CategoryItem {
    return CategoryItem(
        id = id,
        name = name,
        nameGerman = nameGerman,
        createdAt = createdAt,
        bglNumber = bglNumber ?: "",
        bglVariant = bglVariant ?: "",
        orderId = orderId ?: 0,
        main = main ?: "",
        children = children.map { it.toDomain() }
    )
}

