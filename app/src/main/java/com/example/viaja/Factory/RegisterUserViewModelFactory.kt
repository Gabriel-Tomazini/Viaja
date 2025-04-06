package com.example.viaja.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viaja.ViewModel.RegisterUserViewModel
import com.example.viaja.dao.UserDao

class RegisterUserViewModelFactory(
    private val userDao: UserDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterUserViewModel(userDao) as T
    }
}