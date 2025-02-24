package note.reader.controller

import note.reader.model.Document
import note.reader.model.Note

class NoteController (
    val document: Document
) {
    var notes: MutableMap<String, Note> = document.notes


    fun saveNote() {
        // save note to file
    }

    fun deleteNote() {
        // delete note from file
    }

    fun updateNote() {
        // update note in file
    }


}