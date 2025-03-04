package note.reader.controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import note.reader.model.Document
import note.reader.model.enums.DocumentFormats
import note.reader.model.enums.Layouts
import java.io.File

@Serializable
data class ProgramStateData(
    val currentLayout: Layouts,
    val currentDocument: Document,
    val currentDocumentFormat: DocumentFormats,
    val currentPage: Int,
    val currentPageCount: Int,
    val currentIdIndex: Int
)

class ProgramState {
    val filePath = FileSystemController.documents_folder + "\\state.json"
    private val _currentLayout: MutableState<Layouts> = mutableStateOf(Layouts.HOME)

    var currentLayout: Layouts
        get() = _currentLayout.value
        set(value) {
            _currentLayout.value = value
            saveState()
        }

    private val _currentDocument: MutableState<Document> = mutableStateOf(Document())
    var currentDocument: Document
        get() = _currentDocument.value
        set(value) {
            _currentDocument.value = value
            currentDocumentFormat = currentDocument.title.substringAfterLast('.')
                .uppercase().let { DocumentFormats.valueOf(it) }
            saveState()
        }

    private val _currentDocumentFormat: MutableState<DocumentFormats> = mutableStateOf(DocumentFormats.PDF)
    var currentDocumentFormat: DocumentFormats
        get() = _currentDocumentFormat.value
        set(value) {
            _currentDocumentFormat.value = value
            saveState()
        }

    private val _currentPage: MutableState<Int> = mutableStateOf(0)
    var currentPage: Int
        get() = _currentPage.value
        set(value) {
            _currentPage.value = value
            saveState()
        }

    private val _currentPageCount: MutableState<Int> = mutableStateOf(0)
    var currentPageCount: Int
        get() = _currentPageCount.value
        set(value) {
            _currentPageCount.value = value
            println("pagecountChanged: $value")
            saveState()
        }


    private val _currentIdIndex: MutableState<Int> = mutableStateOf(0)
    var currentIdIndex: Int = _currentIdIndex.value
        // Getter generates note ids
        get() = _currentIdIndex.value

    fun incCurrentIdIndex(): Int {
        var nextIdIndex:Int = _currentIdIndex.value.inc()
        _currentIdIndex.value = _currentIdIndex.value.inc()
        nextIdIndex = _currentIdIndex.value
        return nextIdIndex
    }

    override fun toString(): String {
        return "ProgramState(currentLayout=$_currentLayout, currentDocument=$_currentDocument, currentDocumentFormat=$_currentDocumentFormat)"
    }

    fun saveState() {
        val stateData = ProgramStateData(
            currentLayout = currentLayout,
            currentDocument = currentDocument,
            currentDocumentFormat = currentDocumentFormat,
            currentPage = currentPage,
            currentPageCount = currentPageCount,
            currentIdIndex = currentIdIndex
        )
        val json = Json.encodeToString(ProgramStateData.serializer(), stateData)
        File(filePath).writeText(json)
    }

    fun loadState() {
        if (!File(filePath).exists()) { return }
        val json = File(filePath).readText()
        val stateData: ProgramStateData = Json.decodeFromString(ProgramStateData.serializer(), json)

        _currentLayout.value = stateData.currentLayout
        _currentDocument.value = stateData.currentDocument
        _currentDocumentFormat.value = stateData.currentDocumentFormat
        _currentPage.value = stateData.currentPage
        _currentPageCount.value = stateData.currentPageCount
        _currentIdIndex.value = stateData.currentIdIndex
    }

    init {
        if (File(filePath).exists()) {
            loadState()
        } else {
            saveState()
        }
    }
}

object ProgramStateSingleton {
    val instance = ProgramState()
}