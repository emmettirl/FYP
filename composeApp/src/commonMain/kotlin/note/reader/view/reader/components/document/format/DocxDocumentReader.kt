package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable
import note.reader.controller.conversion.DocumentConverter
import java.io.File


@Composable
fun DocxDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val docxPath = if (documentPath.endsWith(".doc")) {
        val docFile = File(documentPath)
        DocumentConverter.convertDocToDocx(docFile, "temp.docx").absolutePath
    } else {
        documentPath
    }

    val path = DocumentConverter.convertDocxToPdf(docxPath, "temp.pdf")
    PdfDocumentReader(path, currentPage, onPageChange, onPageCountChange)
}