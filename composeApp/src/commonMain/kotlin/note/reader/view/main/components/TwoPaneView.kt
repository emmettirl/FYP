package note.reader.view.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import note.reader.view.main.components.document.DocumentReader
import note.reader.view.main.components.notes.NoteEditor

@Composable
fun TwoPaneView() {

    Row(modifier = Modifier.fillMaxSize()) {
        val surfaceModifier: Modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(8.dp)

        Surface(
            modifier = surfaceModifier
        ) {
            DocumentReader()
        }
        Surface(
            modifier = surfaceModifier
        ) {
            NoteEditor()
        }
    }
}