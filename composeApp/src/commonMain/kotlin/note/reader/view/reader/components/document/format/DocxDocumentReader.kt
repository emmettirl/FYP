package note.reader.view.reader.components.document.format

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.apache.poi.hwpf.HWPFDocument
import org.apache.poi.hwpf.usermodel.Picture
import org.apache.poi.hwpf.usermodel.Range
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.FileInputStream
import java.io.InputStream
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Composable
fun DocxDocumentReader(
    documentPath: String,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onPageCountChange: (Int) -> Unit
) {
    val fileExtension = documentPath.substringAfterLast('.')
    val paragraphs: List<String>
    val images: List<BufferedImage>

    if (fileExtension.equals("docx", ignoreCase = true)) {
        val document = XWPFDocument(FileInputStream(documentPath))
        paragraphs = document.paragraphs.map { it.text }
        images = document.allPictures.map { picture ->
            val imageStream: InputStream = picture.data.inputStream()
            ImageIO.read(imageStream)
        }
    } else if (fileExtension.equals("doc", ignoreCase = true)) {
        val document = HWPFDocument(FileInputStream(documentPath))
        val range: Range = document.range
        paragraphs = (0 until range.numParagraphs()).map { range.getParagraph(it).text() }
        images = document.picturesTable.allPictures.map { picture ->
            val imageStream: InputStream = picture.content.inputStream()
            ImageIO.read(imageStream)
        }
    } else {
        throw IllegalArgumentException("Unsupported file format: $fileExtension")
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Document Reader",
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        paragraphs.forEach { paragraph ->
            Text(
                text = paragraph,
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
        images.forEach { bufferedImage ->
            val imageBitmap = bufferedImage.toComposeImageBitmap()
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}

fun BufferedImage.toComposeImageBitmap(): ImageBitmap {
    val width = this.width
    val height = this.height
    val pixels = IntArray(width * height)
    this.getRGB(0, 0, width, height, pixels, 0, width)
    val imageBitmap = ImageBitmap(width, height, ImageBitmapConfig.Argb8888)
    // Assuming you have a method to set pixels in ImageBitmap
    // imageBitmap.setPixels(pixels)
    return imageBitmap
}