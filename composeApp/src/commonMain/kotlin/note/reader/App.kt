package note.reader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        MainLayout()
    }
}

@Composable
fun TwoPaneView() {
    var currentPage by remember { mutableStateOf(0) }

    Row(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            DocumentReader(currentPage = currentPage, onPageChange = { newPage ->
                currentPage = newPage})
        }
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            NoteEditor(documentPage = currentPage)
        }
    }

}

@Composable
fun MainLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        MenuBar("Top Menu")
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            TwoPaneView()
        }
        MenuBar("Bottom Menu")
    }
}

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