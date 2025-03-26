package com.example.viaja.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viaja.Components.MyTextField
import com.example.viaja.ViewModel.RegisterUserViewModel
import com.example.viaja.ui.theme.ViajaTheme

@Composable
fun RegisterScreen(
    RegisterUserViewModel: RegisterUserViewModel = viewModel(),
    onNavigateTo: (String) -> Unit
) {
    val registerUser = RegisterUserViewModel.uiState.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

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
            verticalArrangement = Arrangement.Center
        ) {
            MyTextField(
                value = registerUser.value.user,
                onValueChange = { RegisterUserViewModel.onUserChange(it) },
                label = "Usuário"
            )
            MyTextField(
                value = registerUser.value.name,
                onValueChange = { RegisterUserViewModel.onNameChange(it) },
                label = "Nome"
            )
            MyTextField(
                value = registerUser.value.email,
                onValueChange = {
                    RegisterUserViewModel.onEmailChange(it)
                    emailError = !isValidEmail(it)
                },
                label = "E-mail",
                isError = emailError
            )

            if (emailError) {
                Text(
                    text = "E-mail inválido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            OutlinedTextField(
                value = registerUser.value.password,
                onValueChange = { RegisterUserViewModel.onPasswordChange(it) },
                singleLine = true,
                label = { Text(text = "Senha") },
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

            OutlinedTextField(
                value = registerUser.value.confirmPassword,
                onValueChange = {
                    RegisterUserViewModel.onConfirmPasswordChange(it)
                    passwordError = it != registerUser.value.password
                },
                singleLine = true,
                label = { Text(text = "Confirmar senha") },
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (confirmPasswordVisible) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                isError = passwordError
            )

            if (passwordError) {
                Text(
                    text = "As senhas não coincidem",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { onNavigateTo("LoginScreen") },
                    modifier = Modifier.weight(1f),
                    enabled = !emailError && !passwordError
                ) {
                    Text(text = "Entrar")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RegisterScreenPreview() {
    ViajaTheme {
        RegisterScreen(onNavigateTo = {})
    }
}
