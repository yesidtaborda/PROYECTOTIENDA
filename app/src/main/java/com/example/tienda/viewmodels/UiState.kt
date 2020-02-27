package com.example.tienda.viewmodels

sealed class UiState {
    object Loading: UiState()
    class OnSuccess<T> (val data: T): UiState()
    class OnError(val message: String): UiState()
}