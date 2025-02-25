package note.reader.controller

import note.reader.model.Document
import note.reader.model.Note
import java.io.File

class NoteController (
    val document: Document
) {
    var notes: MutableMap<String, Note> = document.notes


    fun saveNote(note: Note) {
        File(FileSystemController.documents_folder+"\\" + note.source_name+ "\\" + note.note_id.toString() + ".md").writeText(note.content)
    }

    fun deleteNote() {
        // delete note from file
    }

    fun updateNote() {
        // update note in file
    }


}