package note.reader.controller.conversion

import java.io.File

object LibreOfficeDriver {

    fun getLibreOfficePath():String {
        val libreOfficePath: String = "resources\\LibreOfficePortable"
        val executable: String = "LibreOfficeBasePortable.exe"

        var path = File(libreOfficePath, executable).absolutePath

        return path
    }

    fun converDocToDocx(docPath: String, tempDocxPath: String):File {
        val libreOfficePath = getLibreOfficePath()
        val process = ProcessBuilder(libreOfficePath, docPath, tempDocxPath).start()
        process.waitFor()
        return File(tempDocxPath)
    }
}