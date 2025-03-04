package note.reader.view.reader.components.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import note.reader.controller.NoteController
import note.reader.controller.ProgramStateSingleton
import note.reader.model.Note
import note.reader.model.NoteState

@Composable
fun NoteEditor(
    ) {

    var initialized by remember { mutableStateOf( false) }
    var ready by remember { mutableStateOf(false) }

    val noteController = NoteController(ProgramStateSingleton.instance.currentDocument)
    var noteStates: List<NoteState> by remember { mutableStateOf(listOf()) }


    while (noteStates.size.coerceAtLeast(1) < ProgramStateSingleton.instance.currentPageCount) {
        val state = rememberRichTextState()
        noteStates+= NoteState(
            note = Note(
                note_id = ProgramStateSingleton.instance.incCurrentIdIndex(),
                source_name = ProgramStateSingleton.instance.currentDocument.title,
                source_page = noteStates.size,
                content = ""
            ),
            noteRichTextState = state
        )
        if(noteStates.size == ProgramStateSingleton.instance.currentPageCount) {
            initialized = true
        }
    }

    SideEffect() {

        var loadedNotes = noteController.loadDocumentNotes()

        if (initialized && !ready) {
            for (note in loadedNotes) {
                noteStates[note.source_page].note = note
                noteStates[note.source_page].noteRichTextState.setText(note.content)
            }
            ready = true
        }

    }

    if (initialized && ready) {
        for (noteState in noteStates) {
            LaunchedEffect(noteState.noteRichTextState.toMarkdown()){
                noteState.note.content = noteState.noteRichTextState.toMarkdown()
                noteController.saveNote(noteState.note)
            }
        }
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F,
        pageCount = { noteStates.size }
    )

    LaunchedEffect(ProgramStateSingleton.instance.currentPage) {
        pagerState.animateScrollToPage(ProgramStateSingleton.instance.currentPage)
    }

    Column {
        Text(
            text = "Notes Editor",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Page: ${ProgramStateSingleton.instance.currentPage + 1} / ${noteStates.size}",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                // Ensure that a rich text state exists for compose, which will be overwritten by current state when loaded
                val RTState: RichTextState
                if (noteStates.size > 0) {
                    RTState = noteStates[page].noteRichTextState
                } else {
                    RTState = rememberRichTextState()
                    noteStates += NoteState(
                        note = Note(
                            note_id = ProgramStateSingleton.instance.incCurrentIdIndex(),
                            source_name = ProgramStateSingleton.instance.currentDocument.title,
                            source_page = page,
                            content = ""
                        ),
                        noteRichTextState = RTState
                    )
                }
                RichTextEditor(
                    modifier = Modifier.fillMaxSize(),
                    state = RTState
                )
            }
        }
    }
}

