package note.reader.view.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import note.reader.view.main.document.DocumentReader
import note.reader.view.main.notes.NoteEditor

@Composable
fun TwoPaneView() {
    var currentPage by remember { mutableStateOf(0) }
    var documentPageCount by remember { mutableStateOf(0) }

    Row(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            DocumentReader(
                currentPage = currentPage,
                onPageChange = { newPage -> currentPage = newPage },
                onPageCountChange = { pageCount -> documentPageCount = pageCount }
            )
        }
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            NoteEditor(documentPage = currentPage, pageCount = documentPageCount)
        }
    }

}