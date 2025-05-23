package note.reader

import androidx.compose.runtime.Composable
import note.reader.controller.FileSystemController
import note.reader.controller.ProgramStateSingleton
import note.reader.model.enums.Layouts
import note.reader.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import note.reader.view.home.HomeLayout as Home
import note.reader.view.reader.ReaderLayout as Reader


@Composable
@Preview
fun App() {
    val programState = ProgramStateSingleton.instance

    AppTheme(darkTheme = programState.isDarkTheme) {
        FileSystemController

            when (programState.currentLayout) {
                Layouts.READER -> Reader()
                Layouts.HOME -> Home()
            }
    }
}

