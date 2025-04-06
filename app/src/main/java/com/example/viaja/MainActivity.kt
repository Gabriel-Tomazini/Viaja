package com.example.viaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.viaja.Components.NavigationsItems
import com.example.viaja.Screens.MainScreen
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
fun MyApp() {
    val navController = rememberNavController()

    var isUserLoggedIn by remember { mutableStateOf(false) }

    val items = listOf(
        NavigationsItems("Home", Icons.Filled.Home),
        NavigationsItems("New Travel", Icons.Filled.AccountCircle),
        NavigationsItems("Sobre", Icons.Filled.Notifications)
    )

    var selectedItemIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            if (isUserLoggedIn) {
                TopAppBar(
                    title = { Text(text = "Viaja+", fontSize = 30.sp) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = Color.White
                    )
                )
            }
        },
        bottomBar = {
            if (isUserLoggedIn) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                val newRoute = when (index) {
                                    0 -> "Home"
                                    1 -> "NewTravelScreen"
                                    2 -> "Sobre"
                                    else -> "Home"
                                }

                                if (newRoute != navController.currentDestination?.route) {
                                    navController.navigate(newRoute) {
                                        popUpTo(navController.graph.findStartDestination().route!!) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }

                                selectedItemIndex = index
                            },
                            icon = {
                                Icon(
                                    imageVector = item.logo,
                                    contentDescription = item.name,
                                    tint = if (selectedItemIndex == index) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onPrimary
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = "LoginScreen"
            ) {
                composable(route = "MainScreen"){
                    MainScreen(onNavigateTo = {
                        navController.navigate(it)
                    })
                }
                composable(route = "LoginScreen") {
                    LoginScreen(onNavigateTo = { route ->
                        if (route == "MainScreen") {
                            isUserLoggedIn = true
                        }
                        navController.navigate(route)
                    })
                }
                composable(route = "RegisterScreen") {
                    RegisterScreen(
                        onNavigateTo = { navController.navigate(it) }
                    )
                }
                composable("Home") {
                    MainScreen(onNavigateTo = {
                    navController.navigate(it) })
                }
                composable("NewTravelScreen") {
                    NewTravelScreen(onNavigateTo = { navController.navigate(it) })
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