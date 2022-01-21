package com.example.chargeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chargeapp.model.User


@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun readAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}