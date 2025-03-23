package com.example.viaja.Screens

import ImagemLocal
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.viaja.Components.MyTextField
import com.example.viaja.ui.theme.ViajaTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cadastrousuario.Screens.LoginUserViewModel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun LoginScreen(
    LoginUserViewModel: LoginUserViewModel = viewModel(),
    onNavigateTo: (String) -> Unit
) {

    var loginUser = LoginUserViewModel.uiState.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(45.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
                .padding(32.dp)
                .align(Alignment.Center)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyTextField(
                value = loginUser.value.user,
                onValueChange = { LoginUserViewModel.onUserChange(it) },
                label = "Usuário"
            )
            OutlinedTextField(
                value = loginUser.value.password,
                onValueChange = { LoginUserViewModel.onPasswordChange(it) },
                singleLine = true,
                label = { Text(text = "Confirmar senha") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(onClick = { onNavigateTo("MainScreen") },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "Entrar")
                }
                Button(onClick = { onNavigateTo("RegisterScreen") },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "Cadastrar")
                }
                Button(onClick = { onNavigateTo("NewTravelScreen") },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "New Travel")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview(){
    ViajaTheme {
        LoginScreen(onNavigateTo = {})
    }
}