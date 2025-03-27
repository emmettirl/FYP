package note.reader.view.reader.components.document

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import note.reader.controller.ProgramStateSingleton
import note.reader.controller.conversion.CalibreDriver
import note.reader.model.enums.DocumentFormats
import note.reader.view.reader.components.document.format.DocxDocumentReader
import note.reader.view.reader.components.document.format.EpubDocumentReader
import note.reader.view.reader.components.document.format.PdfDocumentReader
import note.reader.view.reader.components.document.format.PptxDocumentReader

@Composable
fun DocumentReader(
    currentPage: Int = ProgramStateSingleton.instance.currentPage,
    onPageChange: (Int) -> Unit = { newPage -> ProgramStateSingleton.instance.currentPage = newPage },
    onPageCountChange: (Int) -> Unit = { pageCount -> ProgramStateSingleton.instance.currentPageCount = pageCount }
) {
    Column {
        Text(
            text = "Document Reader",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Page: ${currentPage + 1} / ${ProgramStateSingleton.instance.currentPageCount}",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        var currentDocumentType =
            ProgramStateSingleton.instance.currentDocument.path.substringAfterLast(".").lowercase()
        if (currentDocumentType in DocumentFormats.entries.toString().lowercase()) {
            when (currentDocumentType) {
                "pdf" -> {
                    PdfDocumentReader(
                        documentPath = ProgramStateSingleton.instance.currentDocument.path,
                        currentPage = currentPage,
                        onPageChange = onPageChange,
                        onPageCountChange = onPageCountChange
                    )
                }
                "docx", "doc" -> {
                     DocxDocumentReader(
                         documentPath = ProgramStateSingleton.instance.currentDocument.path,
                         currentPage = currentPage,
                         onPageChange = onPageChange,
                         onPageCountChange = onPageCountChange
                     )
                }

                "pptx" -> {
                     PptxDocumentReader(
                         documentPath = ProgramStateSingleton.instance.currentDocument.path,
                         currentPage = currentPage,
                         onPageChange = onPageChange,
                         onPageCountChange = onPageCountChange
                     )
                }

                "epub" -> {
                    EpubDocumentReader(
                        documentPath = ProgramStateSingleton.instance.currentDocument.path,
                        currentPage = currentPage,
                        onPageChange = onPageChange,
                        onPageCountChange = onPageCountChange
                    )
                }

                else -> {
                    println("Unsupported file type")
                }
            }
        }
    }
}