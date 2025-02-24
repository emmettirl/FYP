package note.reader

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(1800.dp, 960.dp),
        placement = WindowPlacement.Maximized,
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "NoteReader",
        state = state
    ) {
        App()
    }
}