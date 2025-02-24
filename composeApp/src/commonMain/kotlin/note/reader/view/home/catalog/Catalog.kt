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
        var bgColor: Color
        if (key.substringAfterLast(".").lowercase() in DocumentFormats.entries.toString().lowercase()) {
            bgColor = Color.Gray
        } else {
            bgColor = Color.Red
        }

        Button(
            onClick = { println(key) },
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