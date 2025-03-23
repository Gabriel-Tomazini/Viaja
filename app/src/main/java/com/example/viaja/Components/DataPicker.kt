import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun MyDatePicker() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val selectedDate = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate.value = "$dayOfMonth/${month + 1}/$year" // Formatando a data
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (selectedDate.value.isEmpty()) "Selecione uma data" else "Data: ${selectedDate.value}",
            modifier = Modifier
                .clickable { datePickerDialog.show() }
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { datePickerDialog.show() }) {
            Text("Abrir DatePicker")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyDatePickerPreview() {
    MyDatePicker()
}
