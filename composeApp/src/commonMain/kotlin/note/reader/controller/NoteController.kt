package note.reader.controller

import note.reader.model.Document
import note.reader.model.Note
import java.io.File

class NoteController (
    val document: Document
) {
    var notes: MutableMap<String, Note> = document.notes

    fun saveNote(note: Note) {
        val filepath = FileSystemController.documents_folder+"\\" + note.source_name.substringBeforeLast(".") + "_" + note.source_name.substringAfterLast(".") + "\\" + note.source_page.toString() + ".md"
        // Open file if it exists, create it if it doesn't
        val file = File(filepath)
        if (!file.exists()) {
            file.createNewFile()
        }

        file.writeText(note.note_id.toString() + "\n" + note.content)
    }

    fun loadNote(document: Document, source_page: Int): Note? {

        val file = File(FileSystemController.documents_folder+"\\" + document.title.substringBeforeLast(".") + "_" + document.title.substringAfterLast(".") +  "\\" + source_page.toString() + ".md")
        if (file.exists()) {
            val lines = file.readLines()

            var i = 1
            var file_content = ""
            while (i < lines.size) {
                file_content += lines[i] + "\n"
                i++
            }
            return Note(
                source_name = document.title,
                source_page = source_page,
                note_id = lines[0].toInt(),
                content = file_content
            )
        }
        return null
    }

    fun deleteNote() {
        // delete note from file
    }

    fun updateNote() {
        // update note in file
    }

    fun loadDocumentNotes(): List<Note> {
        var loaded_notes: ArrayList<Note> = ArrayList()

        for (i in 0 until ProgramStateSingleton.instance.currentPageCount) {
            val loaded_note = loadNote(ProgramStateSingleton.instance.currentDocument, i)
            if (loaded_note != null) {
                loaded_notes += loaded_note
            }
        }
        return loaded_notes
    }
}