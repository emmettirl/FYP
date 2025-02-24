package note.reader.view.home.catalog

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import note.reader.controller.FileSystemController
import note.reader.model.enums.DocumentFormats

@Composable
fun Catalog(modifier: Modifier) {

    for (key in FileSystemController.document_map.keys){
        val supportedDocType: Boolean = (key.substringAfterLast(".").lowercase() in DocumentFormats.entries.toString().lowercase())
        val bgColor = if (!supportedDocType) {Color.Red} else {Color.Gray}
        val clickAction: () -> Unit = if (supportedDocType) { { println(key) } } else { { println("Unsupported file type") } }

        Button(
            onClick = clickAction,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = bgColor,
                contentColor = Color.White
            ),
            modifier = modifier
        ){
            Text(
                text = FileSystemController.document_map[key]!!.title,
                textAlign = TextAlign.Center
            )
        }
    }


}