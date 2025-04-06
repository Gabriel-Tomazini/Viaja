package com.example.viaja.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viaja.dao.TravelDao
import com.example.viaja.entity.Travel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class NewTravel(
    val destino: String = "",
    val tipoViagem: String = "",
    val dataInicio: String = "",
    val dataFinal: String = "",
    val orcamento: Double = 0.0,
    val errorMessage: String = "",
    val isSaved: Boolean = false
){
    fun toTravel(): Travel {
        return Travel(
            destino = destino,
            tipoViagem = tipoViagem,
            dataInicio = dataInicio,
            dataFinal = dataFinal,
            orcamento = orcamento
        )
    }
}

class NewTravelViewModel(
    private val travelDao: TravelDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewTravel())
    val uiState: StateFlow<NewTravel> = _uiState.asStateFlow()

    fun onDestinoChange(destino: String) {
        _uiState.value = _uiState.value.copy(destino = destino)
    }

    fun onDataInicioChange(dataInicio: String) {
        _uiState.value = _uiState.value.copy(dataInicio = dataInicio)
    }

    fun onDataFinalChange(dataFinal: String) {
        _uiState.value = _uiState.value.copy(dataFinal = dataFinal)
    }

    fun onOrcamentoChange(orcamento: Double) {
        _uiState.value = _uiState.value.copy(orcamento = orcamento)
    }

    fun registerTravel() {
        try {
            viewModelScope.launch {
                travelDao.insert(_uiState.value.toTravel())
                _uiState.value = _uiState.value.copy(isSaved = true)
            }
        }
        catch (e: Exception) {
            _uiState.value = _uiState.value.copy(errorMessage = e.message ?: "Unknow error")
        }
    }

    fun cleanDisplayValues(){
        _uiState.value = _uiState.value.copy(
            isSaved = false,
            errorMessage = ""
        )
    }
}
