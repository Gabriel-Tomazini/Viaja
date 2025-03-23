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
    val context = LocalContext.current
    val drawable: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.logo, null)

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