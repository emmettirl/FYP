package note.reader.view.reader.components.document.format

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zt64.compose.pdf.component.PdfPage
import dev.zt64.compose.pdf.rememberLocalPdfState
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun PdfDocumentReader(
    documentPath: String,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val pdfState = rememberLocalPdfState(File(documentPath))
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pdfState.pageCount })
    val zoomLevel = remember { mutableStateOf(1f) }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onPageChange(page)
        }
    }

    LaunchedEffect(pdfState.pageCount) {
        onPageCountChange(pdfState.pageCount)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Zoom Controls
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { zoomLevel.value = (zoomLevel.value - 0.1f).coerceAtLeast(0.5f) }) {
                Text("+\uD83D\uDD0D", fontSize = 16.sp)
            }
            Button(onClick = { zoomLevel.value = (zoomLevel.value + 0.1f).coerceAtMost(3f) }) {
                Text("-\uD83D\uDD0D", fontSize = 16.sp)
            }
        }

        // PDF Viewer
        Box(
            modifier = Modifier.weight(1f).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                PdfPage(
                    state = pdfState,
                    index = page,
                    modifier = Modifier.fillMaxSize().graphicsLayer(scaleX = zoomLevel.value, scaleY = zoomLevel.value)                )
            }
        }

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage > 0) {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Previous Page",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text("  Previous Page", fontSize = 16.sp)
            }
            Button(onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pdfState.pageCount - 1) {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }
            }) {
                Text("Next Page  ", fontSize = 16.sp)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next Page",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}