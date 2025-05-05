package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable
import note.reader.controller.conversion.DocumentConverter
import java.io.File


@Composable
fun HtmlDocumentReader(
    documentPath: String,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val pdfPath: String
    if (documentPath.endsWith(".html")) {
        val tempDir = File(System.getProperty("java.io.tmpdir"), "note_reader_temp").apply { mkdirs() }


        if (!File(tempDir, documentPath).exists()) {
            pdfPath = DocumentConverter.convertHtmlToPdf(documentPath, )
        } else {
            println("pdfPath file already exists: ${File(tempDir, documentPath).absolutePath}")
            pdfPath = documentPath
        }

    } else {
        pdfPath = documentPath
    }

    PdfDocumentReader(pdfPath, onPageChange, onPageCountChange)
}