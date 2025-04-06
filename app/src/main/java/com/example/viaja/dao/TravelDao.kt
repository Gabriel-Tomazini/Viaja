// TravelDao.kt
package com.example.viaja.dao

import androidx.room.*
import com.example.viaja.entity.Travel

@Dao
interface TravelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(travel: Travel)

    @Update
    suspend fun update(travel: Travel)

    @Upsert
    suspend fun upsert(travel: Travel)

    @Delete
    suspend fun delete(travel: Travel)

    @Query("SELECT * FROM travel WHERE id = :id")
    suspend fun findById(id: Int): Travel?
}
