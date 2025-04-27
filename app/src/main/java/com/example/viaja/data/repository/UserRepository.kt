package com.example.viaja.data.repository

import com.example.viaja.data.Entity.User
import com.example.viaja.data.dao.UserDao

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun getUser(email: String, password: String) = userDao.getUser(email, password)
}