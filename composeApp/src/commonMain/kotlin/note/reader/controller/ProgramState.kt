package note.reader.controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import note.reader.model.enums.DocumentFormats
import note.reader.model.enums.Layouts

object ProgramState {
    private val _currentLayout: MutableState<Layouts> = mutableStateOf(Layouts.HOME)
    var currentLayout: Layouts
        get() = _currentLayout.value
        set(value) {
            _currentLayout.value = value
        }

    private var _currentDocumentFormat: MutableState<DocumentFormats> = mutableStateOf(DocumentFormats.PDF)
    var currentDocumentFormat: DocumentFormats
        get() = _currentDocumentFormat.value
        set(value) {
            _currentDocumentFormat.value = value
        }

    override fun toString(): String {
        return "ProgramState (currentLayout: $currentLayout, currentDocumentFormat: $currentDocumentFormat)"
    }
}