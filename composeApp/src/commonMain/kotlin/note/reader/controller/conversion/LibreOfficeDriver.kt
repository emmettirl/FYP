package note.reader.controller.conversion

import java.io.File

object LibreOfficeDriver {

    fun getLibreOfficePath(): String {
        val libreOfficePath = "resources\\LibreOffice"
        val executable = "\\App\\libreoffice\\program\\soffice.exe"
        val path = File(libreOfficePath, executable).absolutePath

        println("LibreOffice Executable Path: $path")
        return path
    }

    fun convertDocToDocx(docPath: String, temp_dir: File): String {
        val libreOfficePath = getLibreOfficePath()
        val libreOfficeFile = File(libreOfficePath)
        val tempDocxFile = File(temp_dir, File(docPath).nameWithoutExtension + ".docx")

        if (tempDocxFile.exists()) {
            println("DOCX file already exists: ${tempDocxFile.absolutePath}")
            return tempDocxFile.absolutePath
        }

        if (!libreOfficeFile.exists()) {
            throw RuntimeException("LibreOffice executable not found at: $libreOfficePath")
        }


        val command = listOf(
            libreOfficePath,
            "--headless",
            "--convert-to", "docx",
            docPath,
            "--outdir", temp_dir.absolutePath
        )

        println("Executing command: ${command.joinToString(" ")}")


        val process = ProcessBuilder(
            command
        ).start()
        process.waitFor()


        val output = process.inputStream.bufferedReader().use { it.readText() }
        val errorOutput = process.errorStream.bufferedReader().use { it.readText() }
        println("Process output: $output")

        val exitValue = process.waitFor()
        if (exitValue != 0) {
            throw RuntimeException("LibreOffice conversion failed with exit code $exitValue")
        }


        if (!tempDocxFile.exists() || tempDocxFile.length() == 0L) {
            throw RuntimeException("Conversion failed, output file is empty")
        }

        return tempDocxFile.absolutePath

    }

}