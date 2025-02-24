package note.reader

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import note.reader.controller.FileSystemController
import note.reader.controller.ProgramState
import note.reader.model.enums.Layouts
import org.jetbrains.compose.ui.tooling.preview.Preview
import note.reader.view.home.HomeLayout as Home
import note.reader.view.main.ReaderLayout as Reader


@Composable
@Preview
fun App() {

    // Initialize the file system Singleton
    FileSystemController

    // Run application
    MaterialTheme {
        when(ProgramState.currentLayout) {
            Layouts.READER -> Reader()
            Layouts.HOME -> Home()
        }
    }
}

