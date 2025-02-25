package note.reader.controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import note.reader.model.Document
import note.reader.model.enums.DocumentFormats
import note.reader.model.enums.Layouts


object ProgramState {
    private val _currentLayout: MutableState<Layouts> = mutableStateOf(Layouts.HOME)
    var currentLayout: Layouts
        get() = _currentLayout.value
        set(value) {
            _currentLayout.value = value
        }

    private val _currentDocument: MutableState<Document> = mutableStateOf(Document())
    var currentDocument: Document
        get() = _currentDocument.value
        set(value) {
            _currentDocument.value = value
            currentDocumentFormat = currentDocument.title.substringAfterLast('.')
                .uppercase().let { DocumentFormats.valueOf(it) }
        }

    private val _currentDocumentFormat: MutableState<DocumentFormats> = mutableStateOf(DocumentFormats.PDF)
    var currentDocumentFormat: DocumentFormats
        get() = _currentDocumentFormat.value
        set(value) {
            _currentDocumentFormat.value = value
        }

    private val _currentPage: MutableState<Int> = mutableStateOf(0)
    var currentPage: Int
        get() = _currentPage.value
        set(value) {
            _currentPage.value = value
        }

    private val _currentPageCount: MutableState<Int> = mutableStateOf(0)
    var currentPageCount: Int
        get() = _currentPageCount.value
        set(value) {
            _currentPageCount.value = value
            println("pagecountChanged: $value")
        }


    private val _currentIdIndex: MutableState<Int> = mutableStateOf(0)
    var currentIdIndex: Int = _currentIdIndex.value
        // Getter generates note ids
        get() {
            _currentIdIndex.value = _currentIdIndex.value.inc()
            field = _currentIdIndex.value
            return field
        }

    override fun toString(): String {
        return "ProgramState(currentLayout=$_currentLayout, currentDocument=$_currentDocument, currentDocumentFormat=$_currentDocumentFormat)"
    }
}