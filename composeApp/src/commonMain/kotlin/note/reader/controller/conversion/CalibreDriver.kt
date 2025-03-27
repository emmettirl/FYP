package note.reader.controller.conversion

import java.io.File

object CalibreDriver {

    fun getCalibrePath():String {
        val calibrePath: String = "resources\\Calibre Portable\\Calibre"
        val executable: String = "ebook-convert.exe"

        var path = File(calibrePath, executable).absolutePath


        return path
    }


    fun convertEpubToPdf(epubPath: String, tempPdfPath: String):File {
        val calibrePath = getCalibrePath()
        val process = ProcessBuilder(calibrePath, epubPath, tempPdfPath).start()
        process.waitFor()
        return File(tempPdfPath)
    }
}

fun main() {
    CalibreDriver.getCalibrePath()
    print("Hello")
}