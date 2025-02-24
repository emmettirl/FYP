package note.reader

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import note.reader.controller.FileSystem
import note.reader.view.main.MainLayout
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    val fileSystem = FileSystem()

    MaterialTheme {
        MainLayout()
    }
}

