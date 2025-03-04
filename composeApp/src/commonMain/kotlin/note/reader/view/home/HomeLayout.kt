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
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import note.reader.controller.FileSystemController
import note.reader.controller.ProgramStateSingleton
import note.reader.view.components.menuBar.MenuBar
import note.reader.view.helper_functions.titleCase
import note.reader.view.home.catalog.Catalog
import java.awt.Desktop

@Composable
fun HomeLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        MenuBar(title = titleCase(ProgramStateSingleton.instance.currentLayout.toString()))
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
                    Column(
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                        modifier = Modifier.fillMaxSize().verticalScroll(
                            enabled = true,
                            state = androidx.compose.foundation.rememberScrollState()
                        )
                    ) {
                        Text(
                            text = "Controls",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        )

                        Row(
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val buttonModifier = Modifier.weight(1F).padding(all = 16.dp)

                            Button(modifier = buttonModifier, onClick = {}) {

                                Text(
                                    text = "Import",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }

                            Button(modifier = buttonModifier, onClick = {}) {

                                Text(
                                    text = "Search",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val buttonModifier = Modifier.weight(1F).padding(all = 16.dp)

                            Button(modifier = buttonModifier, onClick = {}) {

                                Text(
                                    text = "Tags",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }

                            Button(modifier = buttonModifier, onClick = {}) {

                                Text(
                                    text = "Export",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val buttonModifier = Modifier.weight(1F).padding(all = 16.dp)

                            Button(modifier = buttonModifier, onClick = {
                                //open the documents folder in the file explorer using a system call

                                Desktop.getDesktop().open(java.io.File(FileSystemController.documents_folder))

                            }) {

                                Text(
                                    text = "Open Folder",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }

                            Button(modifier = buttonModifier, onClick = {}) {

                                Text(
                                    text = "Settings",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                                )
                            }
                        }
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
                    modifier = Modifier.weight(1F).padding(horizontal = 16.dp)
                ) {
                    Column(
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
                        modifier = Modifier.fillMaxSize().verticalScroll(
                            enabled = true,
                            state = androidx.compose.foundation.rememberScrollState()
                        )
                    ) {
                        Text(
                            text = "Catalog",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                        )
                        Catalog(
                            modifier = Modifier.height(50.dp)
                                .padding(horizontal = 5.dp, vertical = 2.dp).fillMaxSize()
                        )
                    }


                }
            }
        }
    }
}