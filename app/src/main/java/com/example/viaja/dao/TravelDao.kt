// TravelDao.kt
package com.example.viaja.dao

import androidx.room.*
import com.example.viaja.entity.Travel
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM travel")
    fun getAllTravels():Flow<List<Travel>>
}
