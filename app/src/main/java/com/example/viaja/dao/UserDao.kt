package com.example.viaja.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.viaja.entity.User

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Upsert
    suspend fun upsert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun findById(id: Int): User?

    @Query("SELECT * FROM usuarios WHERE user = :user AND password = :password")
    suspend fun findByUserAndPassword(user: String, password: String): User?

}