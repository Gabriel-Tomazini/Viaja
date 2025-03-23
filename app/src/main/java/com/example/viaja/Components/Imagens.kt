import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.viaja.R
import com.example.viaja.Screens.LoginScreen
import com.example.viaja.ui.theme.ViajaTheme

@Composable
    fun ImagemLocal() {
    // Obtendo o contexto
    val context = LocalContext.current
    // Usando o contexto para obter o drawable
    val drawable: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.logo, null)

    // Se você quiser exibir a imagem, você pode converter o drawable para ImageBitmap
    drawable?.toBitmap()?.asImageBitmap()?.let {
        Image(bitmap = it, contentDescription = "logo")
    }
}

@Composable
@Preview(showBackground = true)
fun ImagemLogoPreview(){
    ViajaTheme {
        ImagemLocal()
    }
}