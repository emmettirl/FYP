package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable
import note.reader.controller.conversion.DocumentConverter


@Composable
fun PptxDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val path = DocumentConverter.convertPptxToPdf(documentPath, "temp.pdf")
    PdfDocumentReader(path, currentPage, onPageChange, onPageCountChange)
}
