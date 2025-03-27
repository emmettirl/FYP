package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable
import note.reader.controller.conversion.DocumentConverter


@Composable
fun EpubDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val pdfPath = DocumentConverter.convertEpubToPdf(documentPath, "temp.pdf")
    PdfDocumentReader(pdfPath, currentPage, onPageChange, onPageCountChange)
}