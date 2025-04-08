package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable
import note.reader.controller.conversion.DocumentConverter
import note.reader.controller.conversion.DocumentConverter.convertDoctoDocx
import java.io.File


@Composable
fun DocxDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val docxPath: String
    if (documentPath.endsWith(".doc")) {
        val tempDir = File(System.getProperty("java.io.tmpdir"), "note_reader_temp").apply { mkdirs() }

        if (!tempDir.exists()) {
            tempDir.mkdirs()
        }

        if (!File(tempDir, documentPath).exists()) {
            println("DOCX file already exists: ${File(tempDir, documentPath).absolutePath}")
            docxPath = convertDoctoDocx(documentPath)
        } else {
            docxPath = documentPath
        }

    } else {
        docxPath = documentPath
    }

    val path = DocumentConverter.convertDocxToPdf(docxPath, "temp.pdf")

    PdfDocumentReader(path, currentPage, onPageChange, onPageCountChange)
}