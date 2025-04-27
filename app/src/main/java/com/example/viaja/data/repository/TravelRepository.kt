package com.example.viaja.data.repository

import com.example.viaja.data.Entity.Travel
import com.example.viaja.data.dao.TravelDao

class TravelRepository(private val travelDao: TravelDao) {
    suspend fun insertTravel(travel: Travel) = travelDao.insertTravel(travel)
    suspend fun getTravelsByUser(userId: Int) = travelDao.getTravelsByUser(userId)
    suspend fun deleteTravel(travelId: Int) = travelDao.deleteTravel(travelId)
    suspend fun updateTravel(travel: Travel) = travelDao.updateTravel(travel)
    suspend fun getTravelById(travelId: Int): Travel? {
        return travelDao.getTravelById(travelId)
    }
}
