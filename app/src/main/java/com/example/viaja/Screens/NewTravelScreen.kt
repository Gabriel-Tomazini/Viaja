package com.example.viaja.Screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registeruser.components.ErrorDialog
import com.example.viaja.Components.MyTextField
import com.example.viaja.Factory.NewTravelViewModelFactory
import com.example.viaja.ViewModel.NewTravelViewModel
import com.example.viaja.dataBase.AppDataBase
import com.example.viaja.ui.theme.ViajaTheme
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTravelScreen(
    onNavigateTo: (String) -> Unit
) {
    val ctx = LocalContext.current

    val newTravelViewModel: NewTravelViewModel =
        viewModel(
            factory = NewTravelViewModelFactory(AppDataBase.getDatabase(ctx).travelDao())
        )

    val regiterTravel = newTravelViewModel.uiState.collectAsState()

    val uiState by newTravelViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerInicio = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            newTravelViewModel.onDataInicioChange("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val datePickerFinal = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            newTravelViewModel.onDataFinalChange("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    var selectedTripType by remember { mutableStateOf("Viagem de lazer") }

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(45.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MyTextField(
                value = uiState.destino,
                onValueChange = { newTravelViewModel.onDestinoChange(it) },
                label = "Destino"
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Tipo de Viagem:", color = Color.Black, style = MaterialTheme.typography.bodyLarge)
                listOf("Viagem de lazer", "Viagem de negócios").forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedTripType == option,
                            onClick = { selectedTripType = option }
                        )
                        Text(option, color = Color.Black)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .clickable { datePickerInicio.show() }
            ) {
                Text(
                    text = if (uiState.dataInicio.isEmpty()) "Selecione a Data de Início" else uiState.dataInicio,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
                    .clickable { datePickerFinal.show() }
            ) {
                Text(
                    text = if (uiState.dataFinal.isEmpty()) "Selecione a Data Final" else uiState.dataFinal,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }

            MyTextField(
                value = uiState.orcamento.toString(),
                onValueChange = { newTravelViewModel.onOrcamentoChange(it.toDoubleOrNull() ?: 0.0) },
                label = "Orçamento"
            )

            LaunchedEffect(regiterTravel.value.isSaved) {
                if (regiterTravel.value.isSaved) {
                    onNavigateTo("MainScreen")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        newTravelViewModel.registerTravel()
                    },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "Salvar")
                }
            }

            if (regiterTravel.value.errorMessage.isNotBlank()) {
                ErrorDialog(
                    error = regiterTravel.value.errorMessage,
                    onDismissRequest =  {
                        newTravelViewModel.cleanDisplayValues()
                    },
                )
            }

            LaunchedEffect(regiterTravel.value.isSaved) {
                if (regiterTravel.value.isSaved) {
                    Toast.makeText(ctx, "Nova viagem cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                    onNavigateTo("MainScreen")
                    newTravelViewModel.cleanDisplayValues()
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NewTravelScreenPreview() {
    ViajaTheme {
        NewTravelScreen(onNavigateTo = {})
    }
}
