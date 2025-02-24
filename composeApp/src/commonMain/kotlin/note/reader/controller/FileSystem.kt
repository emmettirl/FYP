package note.reader.controller

import note.reader.model.Document
import java.io.File
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

class FileSystem {
    val appDataFolder = System.getenv("APPDATA")
    val documents_folder = "$appDataFolder\\NoteReader"

    val document_map_filename = "document_map.json"
    val document_map_filepath = "$documents_folder\\$document_map_filename"
    var document_map = mutableMapOf<String, Document>()

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
                    // if file is not a directory and not the document_map file
                    if (!file.isDirectory && file.name != document_map_filename) {

                        if (!document_map.containsKey(file.name)) {
                            document_map[file.name] = Document(title = file.name, path = file.path)
                        }
                    }
                }
                for (key in document_map.keys) {
                    // if not in files, mark as missing
                    if (!File(document_map[key]!!.path).exists()) {
                        document_map[key]!!.missing = true
                    }
                    // if marked as missing, but is in files, remove missing flag
                    if (document_map[key]!!.missing && File(document_map[key]!!.path).exists()) {
                        document_map[key]!!.missing = false
                    }
                    // if folder for document notes does not exist, create it
                    println("Value: ${document_map[key].toString()}")
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

    //serialize document_map to json
    fun saveDocumentMap() {
        val json = Json.encodeToString(serializer(), document_map)
        File(document_map_filepath).writeText(json)
        println("saved document_map")
    }

    //deserialize document_map from json
    fun loadDocumentMap() {
        if (!File(document_map_filepath).exists()) { return }
        val json = File(document_map_filepath).readText()

        document_map = Json.decodeFromString(json)
        println("loaded document_map")
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