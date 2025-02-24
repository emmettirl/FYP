package note.reader.model

import kotlinx.serialization.Serializable

@Serializable
class Note(
    val source_name: String,
    val note_id: Int,
    var content: String = ""
) {

}