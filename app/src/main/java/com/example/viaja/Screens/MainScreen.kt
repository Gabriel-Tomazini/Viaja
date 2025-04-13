package com.example.viaja.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viaja.ViewModel.NewTravelViewModel
import com.example.viaja.entity.Travel

@Composable
fun MainScreen(
    onNavigateTo: (String) -> Unit,
    viewModel: NewTravelViewModel
) {
    val travelList by viewModel.allTravels.collectAsState()

    LazyColumn {
        items(travelList) { travel ->
            TravelItem(travel = travel)
        }
    }
}


@Composable
fun TravelItem(travel: Travel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Destino: ${travel.destino}", style = MaterialTheme.typography.titleMedium)
            Text("Tipo: ${travel.tipoViagem}")
            Text("Data: ${travel.dataInicio} até ${travel.dataFinal}")
            Text("Orçamento: R$ %.2f".format(travel.orcamento))
        }
    }
}