package note.reader.view.reader.components.document.format

import androidx.compose.runtime.Composable


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
