package com.example.homework26.presentation.event

sealed class HomeEvent {
    data object GetCategories : HomeEvent()
    data class GetFilteredCategories(val filterKeyWord : String): HomeEvent()
}