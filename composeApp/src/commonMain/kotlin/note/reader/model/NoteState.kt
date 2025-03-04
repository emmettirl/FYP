package note.reader.model

import com.mohamedrejeb.richeditor.model.RichTextState

data class NoteState
    (
    var note: Note,
    var noteRichTextState: RichTextState
            )