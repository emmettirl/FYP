package note.reader.model

import kotlinx.serialization.Serializable

@Serializable
class Document (
    val title: String = "",
    val path: String = "",
    var missing: Boolean = false
){
    val folderPath: String = path.substringBeforeLast(".")+ "_" + path.substringAfterLast(".")

    // dictionary of notes in folder with same name as this file
    val notes: MutableMap<String, Note> = mutableMapOf()


    override fun toString(): String {
        return title + " " + path + " " + "Number of Notes: " + notes.size
    }


}