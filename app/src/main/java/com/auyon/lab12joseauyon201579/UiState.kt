package com.auyon.lab12joseauyon201579

sealed class UiState{

    class Success(val message : String) : UiState()
    class Error (val errorMessage : String) : UiState()
    object Loading : UiState()
    object  Empty : UiState()

}
