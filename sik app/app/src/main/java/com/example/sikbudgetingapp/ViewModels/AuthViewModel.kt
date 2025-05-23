package com.example.sikbudgetingapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikbudget.data.firebase.FirebaseAuthService
import kotlinx.coroutines.launch

class AuthViewModel(private val authService: FirebaseAuthService) : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = authService.login(email, password)
            onResult(result)
        }
    }

    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = authService.register(email, password)
            onResult(result)
        }
    }

    fun logout() {
        authService.logout()
    }
}