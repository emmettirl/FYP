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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import note.reader.controller.NoteController
import note.reader.controller.ProgramStateSingleton
import note.reader.model.Note

@Composable
fun NoteEditor(
    ) {
    val noteController = NoteController(ProgramStateSingleton.instance.currentDocument)


    var notes by remember { mutableStateOf(listOf<Note>()) }
    val noteRichTextStates = remember { mutableStateListOf<RichTextState>() }

        while (noteRichTextStates.size.coerceAtLeast(1) < notes.size) {
            val state = rememberRichTextState()
            state.setText(notes[noteRichTextStates.size].content)
            noteRichTextStates.add(state)
        }

    SideEffect() {
        //ToDo - Load notes from file
        var loadedNotes = noteController.loadDocumentNotes()


        notes = List(ProgramStateSingleton.instance.currentPageCount) {
            Note(
                note_id = ProgramStateSingleton.instance.incCurrentIdIndex(),
                source_name = ProgramStateSingleton.instance.currentDocument.title,
                source_page = it,
                content = ""
            )
        }
    }

    for (state in noteRichTextStates) {
        LaunchedEffect(state.toMarkdown()){
            notes[noteRichTextStates.indexOf(state)].content = state.toMarkdown()
            noteController.saveNote(notes[noteRichTextStates.indexOf(state)])
            println(state.toMarkdown())
        }
    }


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F,
        pageCount = { notes.size }
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
            text = "Page: ${ProgramStateSingleton.instance.currentPage + 1} / ${notes.size}",
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
                if (noteRichTextStates.size > 0) {
                    RTState = noteRichTextStates[page]
                } else {
                    RTState = rememberRichTextState()
                }
                RichTextEditor(
                    modifier = Modifier.fillMaxSize(),
                    state = RTState
                ) }
        }
    }
}