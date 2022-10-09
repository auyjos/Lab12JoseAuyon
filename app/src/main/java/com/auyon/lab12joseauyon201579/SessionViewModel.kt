package com.auyon.lab12joseauyon201579

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SessionViewModel : ViewModel(){
    private lateinit var job: Job
    private val _checkInStatus = MutableStateFlow<SecondViewModel.CheckInStatus>(SecondViewModel.CheckInStatus.Missing)
    val checkInStatus: StateFlow<SecondViewModel.CheckInStatus> = _checkInStatus
    private lateinit var inputKey: TextInputLayout
    private lateinit var inputValue: TextInputLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    sealed class CheckInStatus {
        object Missing : CheckInStatus()
        object Loading : CheckInStatus()
        class Success(val message: String) : CheckInStatus()
        class Failure(val errorMessage: String) : CheckInStatus()
    }

    fun checkIn() {
        job = viewModelScope.launch {

            val key = inputKey.editText!!.text.toString()
            val value = inputValue.editText!!.text.toString()

            editor.putString("auy201579@uvg.edu.gt", key)
            editor.putString("auy201579@uvg.edu.gt", value)
            editor.apply()


            val emailLog = sharedPreferences.getString("auy201579@uvg.edu.gt", null)
            val passLog = sharedPreferences.getString("auy201579@uvg.edu.gt", null)

            delay(10000L)

            if ((0..50).random() % 2 == 0) {
                if (emailLog.equals(key) && passLog.equals(value)) {

                    CheckInStatus.Success("Login")
                } else {
                    CheckInStatus.Failure("Error")
                }
            }


        }
    }

}
