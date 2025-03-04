package note.reader.model

import kotlinx.serialization.Serializable

@Serializable
class Note(
    val source_name: String,
    var source_page: Int = 0,
    val note_id: Int,
    var content: String = ""
) {

}