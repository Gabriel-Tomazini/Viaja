package com.example.viaja.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viaja.ViewModel.NewTravelViewModel
import com.example.viaja.dao.TravelDao

class NewTravelViewModelFactory(
    private val travelDao: TravelDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewTravelViewModel(travelDao) as T
    }
}