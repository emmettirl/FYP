package note.reader.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import note.reader.controller.ProgramState
import note.reader.view.components.menuBar.MenuBar
import note.reader.view.helper_functions.titleCase
import note.reader.view.home.catalog.Catalog

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
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Box(
                   modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
                ) {
                    Column (
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                    ) {
                        Text(
                            text = "Controls",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        )
                    }
                }

                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 16.dp)
                )

                Box(
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
                ) {
                    Column (
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                        modifier = Modifier.fillMaxSize().verticalScroll(
                            enabled = true,
                            state = androidx.compose.foundation.rememberScrollState()
                        )
                    ){
                        Text(
                            text = "Catalog",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        )
                        Catalog(modifier = Modifier.height(50.dp).padding(horizontal = 5.dp, vertical = 2.dp).fillMaxSize())
                    }

                }

            }
        }
    }
}