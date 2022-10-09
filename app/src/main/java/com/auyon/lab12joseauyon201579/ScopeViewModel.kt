package com.auyon.lab12joseauyon201579
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScopeViewModel : ViewModel() {

    private val _username = MutableStateFlow("auy201579@uvg.edu.gt")
    val username: StateFlow<String> = _username

    fun updateUsername(username: String) {
        _username.value = username
    }

}