package note.reader.view.main.components.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zt64.compose.pdf.component.PdfPage
import dev.zt64.compose.pdf.rememberLocalPdfState
import note.reader.controller.ProgramState
import java.io.File

@Composable
fun DocumentReader(
    currentPage: Int = ProgramState.currentPage,
    onPageChange: (Int) -> Unit = { newPage -> ProgramState.currentPage = newPage },
    onPageCountChange: (Int) -> Unit = {pageCount -> ProgramState.currentPageCount = pageCount}
    ) {
    val pdfState = rememberLocalPdfState(File(ProgramState.currentDocument.path))
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pdfState.pageCount })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(page)
        }
    }

    LaunchedEffect(pdfState.pageCount) {
        onPageCountChange(pdfState.pageCount)
    }

    Column {
        Text(
            text = "Document Reader",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Page: ${currentPage + 1} / ${pdfState.pageCount}",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize().background(Color.DarkGray),
            contentAlignment = Alignment.Center,
        ) {
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                PdfPage(
                    state = pdfState,
                    index = page
                )
            }
        }
    }
}