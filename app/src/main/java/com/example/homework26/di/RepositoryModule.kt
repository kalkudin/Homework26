package com.example.homework26.di

import com.example.homework26.data.common.HandleResponse
import com.example.homework26.data.remote.service.CategoriesService
import com.example.homework26.data.repository.CategoriesRepositoryImpl
import com.example.homework26.domain.repository.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCategoriesRepository(handleResponse: HandleResponse, categoriesService: CategoriesService): CategoriesRepository {
        return CategoriesRepositoryImpl(handleResponse = handleResponse, categoriesService = categoriesService)
    }
}