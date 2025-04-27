package com.example.viaja.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viaja.data.Entity.Travel
import com.example.viaja.data.repository.TravelRepository
import kotlinx.coroutines.launch

class TravelViewModel(private val repository: TravelRepository) : ViewModel() {
    fun insertTravel(travel: Travel) {
        viewModelScope.launch {
            repository.insertTravel(travel)
        }
    }

    suspend fun getTravelsByUser(userId: Int): List<Travel> {
        return repository.getTravelsByUser(userId)
    }

    fun deleteTravel(travelId: Int) {
        viewModelScope.launch {
            repository.deleteTravel(travelId)
        }
    }

    fun updateTravel(travel: Travel) {
        viewModelScope.launch {
            repository.updateTravel(travel)
        }
    }

    suspend fun getTravelById(travelId: Int): Travel? {
        return repository.getTravelById(travelId)
    }
}
