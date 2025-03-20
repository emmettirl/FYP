package note.reader.view.reader.components.document.format

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.zt64.compose.pdf.component.PdfPage
import dev.zt64.compose.pdf.rememberLocalPdfState
import java.io.File

@Composable
fun PdfDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val pdfState = rememberLocalPdfState(File(documentPath))
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pdfState.pageCount })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(page)
        }
    }

    LaunchedEffect(pdfState.pageCount) {
        onPageCountChange(pdfState.pageCount)
    }

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