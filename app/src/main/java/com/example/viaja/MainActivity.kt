package com.example.viaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viaja.Screens.LoginScreen
import com.example.viaja.Screens.RegisterScreen
import com.example.viaja.ui.theme.ViajaTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viaja.Screens.NewTravelScreen
import com.example.viaja.ViewModel.RegisterUserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViajaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Viaja+",fontSize = 30.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = Color.White
                )
            )
        }
    ){
        Column (modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = "LoginScreen"
            ){
                composable(route = "LoginScreen"){
                    LoginScreen(onNavigateTo = {
                        navController.navigate(it)
                    })
                }
                composable(route = "RegisterScreen") {
                    val registerUserViewModel: RegisterUserViewModel = viewModel()
                    RegisterScreen(
                        RegisterUserViewModel = registerUserViewModel,
                        onNavigateTo = { navController.navigate(it) }
                    )
                }
                composable(route = "NewTravelScreen"){
                    NewTravelScreen(onNavigateTo = {
                        navController.navigate(it)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ViajaTheme {
        MyApp()
    }
}