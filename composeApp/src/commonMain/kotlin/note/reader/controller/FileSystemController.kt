package note.reader.controller

import kotlinx.serialization.KSerializer
import note.reader.model.Document
import java.io.File
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

object FileSystemController {
    val appDataFolder = System.getenv("APPDATA")
    val documents_folder = "$appDataFolder\\NoteReader"
    val document_map_filename = "document_map.json"
    var document_map = mutableMapOf<String, Document>()

    val mapSerializer: KSerializer<MutableMap<String, Document>> = serializer()

    fun makeDocumentMap(path: String)  {
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdir()
            println("Folder created")
        }

        val read_access = dir.canRead()
        val write_access = dir.canWrite()

        if (read_access && write_access) {
            val files = dir.listFiles()
            if (files != null) {
                for (file in File(path).listFiles()!!) {
                    if (file.extension == "json" || file.extension == "bak") {
                        continue
                    }
                    if (!file.isDirectory && file.name != document_map_filename) {

                        if (!document_map.containsKey(file.name)) {
                            document_map[file.name] = Document(title = file.name, path = file.path)
                        }
                    }
                }
                for (key in document_map.keys) {
                    if (!File(document_map[key]!!.path).exists()) {
                        document_map[key]!!.missing = true
                    }
                    if (document_map[key]!!.missing && File(document_map[key]!!.path).exists()) {
                        document_map[key]!!.missing = false
                    }
                    if (!File(document_map[key]!!.folderPath).isDirectory) {
                        createFolder(document_map[key]!!.folderPath)
                    }
                }
            } else {
                println("No files in folder")
            }
        } else {
            when {
                !read_access -> println("Error | Read Access: $read_access")
                !write_access -> println("Error | Write Access: $write_access")
            }
        }
    }

    fun saveDocumentMap() {
        val document_map_filepath = documents_folder + "\\" + document_map_filename

        val json = Json.encodeToString(mapSerializer, document_map)
        File(document_map_filepath).writeText(json)
    }

    fun loadDocumentMap() {
        val document_map_filepath = documents_folder + "\\" + document_map_filename

        if (!File(document_map_filepath).exists()) { return }
        val json = File(document_map_filepath).readText()

        document_map = Json.decodeFromString(json)
    }

    fun refreshDocumentMap() {
        makeDocumentMap(documents_folder)
    }

    fun remakeDocumentMap() {
        document_map.clear()
        makeDocumentMap(documents_folder)
    }

    fun createFolder(path: String) {
        val folder = File(path)
        if (!folder.exists()) {
            folder.mkdir()
        }
    }

    init {
        println(documents_folder)
        loadDocumentMap()
        makeDocumentMap(documents_folder)
        saveDocumentMap()
        println("init complete")
    }
}