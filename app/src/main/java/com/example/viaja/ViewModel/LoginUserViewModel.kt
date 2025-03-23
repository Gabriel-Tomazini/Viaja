package com.example.cadastrousuario.Screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RegisterUser(
    val user: String = "",
    val password: String = "",
)

class LoginUserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUser())
    val uiState : StateFlow<RegisterUser> = _uiState.asStateFlow()

    fun onUserChange(user: String) {
        _uiState.value = _uiState.value.copy(user = user)
    }
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }
}