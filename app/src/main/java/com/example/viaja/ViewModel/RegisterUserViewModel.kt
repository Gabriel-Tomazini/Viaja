package com.example.viaja.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viaja.dao.UserDao
import com.example.viaja.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUser(
    val user: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String = "",
    val isSaved: Boolean = false

) {
    fun toUser(): User {
        return User(
            user = user,
            name = name,
            email = email,
            password = password
        )
    }
}

class RegisterUserViewModel(
    private val userDao: UserDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUser())
    val uiState : StateFlow<RegisterUser> = _uiState.asStateFlow()

    fun onUserChange(user: String) {
        _uiState.value = _uiState.value.copy(user = user)
    }
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }
    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }
    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun registerUser() {
        try {
            viewModelScope.launch {
                userDao.insert(_uiState.value.toUser())
                _uiState.value = _uiState.value.copy(isSaved = true)
            }
        }
        catch (e: Exception) {
            _uiState.value = _uiState.value.copy(errorMessage = e.message ?: "Unknow error")
        }
    }

    fun cleanDisplayValues(){
        _uiState.value = _uiState.value.copy(
            isSaved = false,
            errorMessage = ""
        )
    }
}