package note.reader.view.components.menuBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

val HORIZONTAL_PADDING = 10.dp

@Composable
fun MenuBar(title: String) {
    Surface(
        modifier = Modifier
            .height(56.dp)
            .background(Color.DarkGray)
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING).align(Alignment.CenterVertically)
            )

            Row(
                modifier = Modifier.padding(horizontal = HORIZONTAL_PADDING).fillMaxWidth().align(Alignment.CenterVertically)
            ) {
                MenuButton(modifier = Modifier.weight(1F).padding(horizontal = 2.dp))
                MenuButton(modifier = Modifier.weight(1F).padding(horizontal = 2.dp))
                MenuButton(modifier = Modifier.weight(1F).padding(horizontal = 2.dp))
                MenuButton(modifier = Modifier.weight(1F).padding(horizontal = 2.dp))
                MenuButton(modifier = Modifier.weight(1F).padding(horizontal = 2.dp))
            }
        }
    }
}



