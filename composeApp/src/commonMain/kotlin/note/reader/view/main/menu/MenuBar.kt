package note.reader.view.main.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MenuBar(title: String) {
    Surface(
        modifier = Modifier
            .height(56.dp)
            .background(Color.DarkGray)
            .fillMaxWidth()
    ) {
        Text(text = title, color = Color.Black, textAlign = TextAlign.Center)
    }
}