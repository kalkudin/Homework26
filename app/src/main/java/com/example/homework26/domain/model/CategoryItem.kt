package com.example.homework26.domain.model

data class CategoryItem(
    val id : String,
    val name : String,
    val nameGerman : String,
    val createdAt : String,
    val bglNumber : String,
    val bglVariant : String,
    val orderId : Int,
    val main : String,
    val children: List<CategoryItem>
)
