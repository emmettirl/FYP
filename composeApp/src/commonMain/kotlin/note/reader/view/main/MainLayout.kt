package note.reader.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import note.reader.controller.ProgramState
import note.reader.view.main.components.TwoPaneView
import note.reader.view.components.menuBar.MenuBar
import note.reader.view.helper_functions.titleCase


@Composable
fun MainLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        MenuBar(title = titleCase(ProgramState.currentLayout.toString()))
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            TwoPaneView()
        }
    }
}