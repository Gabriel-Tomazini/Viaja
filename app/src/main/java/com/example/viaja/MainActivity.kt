package com.example.viaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.viaja.data.UserDatabase
import com.example.viaja.screens.AppNavigation
import com.example.viaja.ui.theme.viajaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = UserDatabase.getDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            ViajaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(paddingValues = innerPadding, database = database)
                }
            }
        }
    }
}
