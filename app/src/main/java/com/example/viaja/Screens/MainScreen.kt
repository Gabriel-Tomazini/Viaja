package com.example.viaja.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.viaja.ui.theme.ViajaTheme

@Composable
fun MainScreen(
    onNavigateTo: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
    Text(text = "Main Screen")
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreenPreview() {
    ViajaTheme {
        MainScreen(onNavigateTo = {})
    }
}