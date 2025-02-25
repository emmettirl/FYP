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

@Composable
fun NoteEditor(
    ) {

    println("pageCount: ${ProgramState.currentPageCount}")
    var notes by remember { mutableStateOf(List(ProgramState.currentPageCount) { "" }) }
    println("notes: $notes")

    var noteRichTextStates = remember { mutableStateListOf<RichTextState>() }

        while (noteRichTextStates.size < notes.size) {
            val state = rememberRichTextState()
            state.setText(notes[noteRichTextStates.size])
            noteRichTextStates.add(rememberRichTextState())
        }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F,
        pageCount = { notes.size }
    )

    LaunchedEffect(ProgramState.currentPage) {
        notes = List(ProgramState.currentPageCount) { "" }
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
                RichTextEditor(
                    modifier = Modifier.fillMaxSize(),
                    state = noteRichTextStates[page],
                )
            }
        }
    }
}