package com.example.homework26.data.remote.service

import com.example.homework26.data.remote.model.CategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesService {
    @GET("/v3/6f722f19-3297-4edd-a249-fe765e8104db?search=title")
    suspend fun getCategories() : Response<List<CategoryDto>>
}