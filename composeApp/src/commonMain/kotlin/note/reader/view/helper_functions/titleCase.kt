package note.reader.view.helper_functions

import java.util.Locale

fun titleCase(string: String): String {
    return string.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}