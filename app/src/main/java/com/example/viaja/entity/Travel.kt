package com.example.viaja.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travel")
data class Travel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val destino: String,
    val tipoViagem: String,
    val dataInicio: String,
    val dataFinal: String,
    val orcamento: Double
)
