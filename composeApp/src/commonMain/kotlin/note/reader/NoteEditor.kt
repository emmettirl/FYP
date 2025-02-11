package note.reader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshotFlow
import com.mohamedrejeb.richeditor.model.RichTextState

@Composable
fun NoteEditor(documentPage: Int) {
    var currentPage = documentPage
    var notes by remember { mutableStateOf(listOf("Test 1", " Test 2", "")) } // Initialize with 3 empty pages
    var noteRichtextStates = remember { mutableStateListOf<RichTextState>() }

    if (noteRichtextStates.isEmpty()) {
        for (note in notes) {
            val state = rememberRichTextState()
            state.setText(note)
            noteRichtextStates += state
        }
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0F,
        pageCount = { notes.size }
    )

    LaunchedEffect(documentPage) {
        pagerState.animateScrollToPage(documentPage)
    }

    Column {
        Text(
            text = "Notes Editor",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Page: ${currentPage + 1} / ${notes.size}",
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
                    state = noteRichtextStates[page],
                )
            }
        }
    }
}