package note.reader.view.main.components.notes

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import note.reader.controller.ProgramState
import note.reader.model.Note

@Composable
fun NoteEditor(
    ) {

    println("pageCount: ${ProgramState.currentPageCount}")
    var notes by remember { mutableStateOf(listOf<Note>()) }
    println("notes: $notes")

    val noteRichTextStates = remember { mutableStateListOf<RichTextState>() }

        while (noteRichTextStates.size.coerceAtLeast(1) < notes.size) {
            val state = rememberRichTextState()
            state.setText(notes[noteRichTextStates.size].content)
            noteRichTextStates.add(rememberRichTextState())
        }

    SideEffect() {
        notes = List(ProgramState.currentPageCount) {
            Note(
                note_id = ProgramState.currentIdIndex,
                source_name = ProgramState.currentDocument.title,
                content = ""
            )
        }
    }


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F,
        pageCount = { notes.size }
    )

    LaunchedEffect(ProgramState.currentPage) {
        pagerState.animateScrollToPage(ProgramState.currentPage)
    }

    Column {
        Text(
            text = "Notes Editor",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Page: ${ProgramState.currentPage + 1} / ${notes.size}",
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
                )
            }
        }
    }
}