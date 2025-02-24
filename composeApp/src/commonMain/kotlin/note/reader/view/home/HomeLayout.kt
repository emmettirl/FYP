package note.reader.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import note.reader.controller.ProgramState
import note.reader.model.enums.Layouts

@Composable
fun HomeLayout() {
    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = { ProgramState.currentLayout = Layouts.MAIN },
            content = { "Go to Main" },
            modifier = Modifier.width(200.dp)
        )
        Text(
            text = ProgramState.currentLayout.toString(),
            color = Color.Black,
            textAlign = TextAlign.Center

        )
    }
}