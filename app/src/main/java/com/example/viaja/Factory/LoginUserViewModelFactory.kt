package com.example.viaja.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viaja.ViewModel.LoginUserViewModel
import com.example.viaja.dao.UserDao

class LoginUserViewModelFactory(
    private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginUserViewModel(userDao) as T
    }
}