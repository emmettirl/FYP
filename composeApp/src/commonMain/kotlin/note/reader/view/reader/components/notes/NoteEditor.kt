package note.reader.view.reader.components.notes

import HtmlScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorColors
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults
import note.reader.controller.NoteController
import note.reader.controller.ProgramStateSingleton
import note.reader.model.Note
import note.reader.model.NoteState


@Composable
fun NoteEditor() {
    var currentScreen by remember { mutableStateOf("NoteEditor") }
    var markdownContent by remember { mutableStateOf("") }

    var initialized by remember { mutableStateOf(false) }
    var ready by remember { mutableStateOf(false) }

    val noteController = NoteController(ProgramStateSingleton.instance.currentDocument)
    var noteStates: List<NoteState> by remember { mutableStateOf(listOf()) }

    while (noteStates.size.coerceAtLeast(1) < ProgramStateSingleton.instance.currentPageCount) {
        val state = rememberRichTextState()
        noteStates += NoteState(
            note = Note(
                note_id = ProgramStateSingleton.instance.incCurrentIdIndex(),
                source_name = ProgramStateSingleton.instance.currentDocument.title,
                source_page = noteStates.size,
                content = ""
            ),
            noteRichTextState = state
        )
        if (noteStates.size == ProgramStateSingleton.instance.currentPageCount) {
            initialized = true
        }
    }

    SideEffect {
        val loadedNotes = noteController.loadDocumentNotes()

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
            LaunchedEffect(noteState.noteRichTextState.toMarkdown()) {
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
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.DarkGray).height(64.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    markdownContent =
                        noteStates.joinToString("\n") { it.noteRichTextState.toMarkdown() }
                    currentScreen = "HtmlScreen"
                },
            ) {
                Text(text = "Render Markdown", color = Color.White)
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            if (currentScreen == "NoteEditor") {
                VerticalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
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
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background),
                        colors = richTextEditorColorsFromTheme(),
                        state = RTState
                    )
                }
            } else if (currentScreen == "HtmlScreen") {
                HtmlScreen(
                    markdown = markdownContent,
                    onBack = { currentScreen = "NoteEditor" }
                )
            }
        }
    }
}

@Composable
fun richTextEditorColorsFromTheme(): RichTextEditorColors {

    return RichTextEditorDefaults.richTextEditorColors(
        textColor = MaterialTheme.colors.onSurface,
        disabledTextColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        containerColor = MaterialTheme.colors.background,
        cursorColor = MaterialTheme.colors.primary,
        errorCursorColor = MaterialTheme.colors.error,
        focusedIndicatorColor = MaterialTheme.colors.primary,
        unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        errorIndicatorColor = MaterialTheme.colors.error,
        disabledIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        focusedLeadingIconColor = MaterialTheme.colors.onSurface,
        unfocusedLeadingIconColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        disabledLeadingIconColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        errorLeadingIconColor = MaterialTheme.colors.error,
        focusedTrailingIconColor = MaterialTheme.colors.onSurface,
        unfocusedTrailingIconColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        disabledTrailingIconColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        errorTrailingIconColor = MaterialTheme.colors.error,
        focusedLabelColor = MaterialTheme.colors.primary,
        unfocusedLabelColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        disabledLabelColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        errorLabelColor = MaterialTheme.colors.error,
        placeholderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        disabledPlaceholderColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        focusedSupportingTextColor = MaterialTheme.colors.onSurface,
        unfocusedSupportingTextColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        disabledSupportingTextColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        errorSupportingTextColor = MaterialTheme.colors.error
    )
}