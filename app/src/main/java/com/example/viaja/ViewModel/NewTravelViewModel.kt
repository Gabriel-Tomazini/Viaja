package com.example.viaja.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NewTravel(
    val destino: String = "",
    val dataInicio: String = "",
    val dataFinal: String = "",
    val orçamento: Double = 0.0
)

class NewTravelViewModel : ViewModel() {
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
        _uiState.value = _uiState.value.copy(orçamento = orcamento)
    }
}
