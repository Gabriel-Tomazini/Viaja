package com.example.viaja.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viaja.dao.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUser(
    val user: String = "",
    val password: String = "",
    val isAuthenticated: Boolean = false,
    val errorMessage: String = ""
)

class LoginUserViewModel(
    private val userDao: UserDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUser())
    val uiState: StateFlow<LoginUser> = _uiState.asStateFlow()

    // Função para alterar o nome de usuário
    fun onUserChange(user: String) {
        _uiState.value = _uiState.value.copy(user = user)
    }

    // Função para alterar a senha
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    // Função para autenticar o usuário
    fun authenticateUser() {
        viewModelScope.launch {
            // Busca o usuário no banco de dados pelo nome de usuário e senha
            val user = userDao.findByUserAndPassword(_uiState.value.user, _uiState.value.password)

            if (user != null) {
                // Se o usuário for encontrado, autentica
                _uiState.value = _uiState.value.copy(isAuthenticated = true, errorMessage = "")
            } else {
                // Se o usuário ou senha estiverem incorretos, exibe a mensagem de erro
                _uiState.value = _uiState.value.copy(isAuthenticated = false, errorMessage = "Usuário ou senha inválidos.")
            }
        }
    }
}
