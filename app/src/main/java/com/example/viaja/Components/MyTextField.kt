package com.example.viaja.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var isTouched = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = value,
        onValueChange = {
            isTouched.value = true
            onValueChange(it)
        },
        singleLine = true,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusEvent {
                if (it.hasFocus) isTouched.value = true
            },
        isError = isTouched.value && (value.isBlank() || isError),
        supportingText = {
            if (isTouched.value && value.isBlank()) {
                Text(text = "Campo $label é obrigatório")
            } else if (isError && errorMessage != null) {
                Text(text = errorMessage)
            }
        }
    )
}
