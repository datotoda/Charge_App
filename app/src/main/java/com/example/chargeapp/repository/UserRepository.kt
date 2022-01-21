package com.example.chargeapp.repository

import androidx.lifecycle.LiveData
import com.example.chargeapp.data.UserDao
import com.example.chargeapp.model.User


class UserRepository (private val userDao: UserDao){
    val readAllUsers: LiveData<List<User>> = userDao.readAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}