package note.reader.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import note.reader.view.main.menu.MenuBar

@Composable
fun MainLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        MenuBar("Top Menu")
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            TwoPaneView()
        }
        MenuBar("Bottom Menu")
    }
}