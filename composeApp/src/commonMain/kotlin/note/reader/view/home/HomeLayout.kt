package note.reader.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import note.reader.controller.ProgramState
import note.reader.model.enums.Layouts
import note.reader.view.components.menuBar.MenuBar
import note.reader.view.helper_functions.titleCase

@Composable
fun HomeLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        MenuBar(title = titleCase(ProgramState.currentLayout.toString()))
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { ProgramState.currentLayout = Layouts.MAIN },
                modifier = Modifier.width(200.dp)
            ){
                Text(
                    text = ProgramState.currentLayout.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}