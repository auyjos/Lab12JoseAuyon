package com.auyon.lab12joseauyon201579

import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.auyon.lab12joseauyon201579.databinding.FragmentLogInBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch




sealed class SecondViewModel : ViewModel() {

        private lateinit var job: Job
        private val _checkInStatus = MutableStateFlow<CheckInStatus>(CheckInStatus.Missing)
        val checkInStatus: StateFlow<CheckInStatus> = _checkInStatus

    sealed class CheckInStatus {
        object Missing : CheckInStatus()
        object Loading : CheckInStatus()
        class Success(val message: String) : CheckInStatus()
        class Failure(val errorMessage: String) : CheckInStatus()
    }

    fun checkIn() {
        job = viewModelScope.launch {

            _checkInStatus.value = CheckInStatus.Loading
            _checkInStatus.value = CheckInStatus.Success(message = "Success")
            _checkInStatus.value = CheckInStatus.Failure(errorMessage = "Error")
            delay(2000L)
        }
    }

    fun reset() {
        if (this::job.isInitialized && job.isActive) {
            job.cancel()
            _checkInStatus.value = CheckInStatus.Missing
        }
    }
}

