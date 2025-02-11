package note.reader

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.io.File

@Composable
fun DocumentReader(currentPage: Int, onPageChange: (Int) -> Unit) {
    val pdfState = rememberLocalPdfState(File("D:\\Documents\\code\\FYP\\SampleFiles\\sample1.pdf"))
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pdfState.pageCount })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(page)
        }
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