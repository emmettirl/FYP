package note.reader.view.reader.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import note.reader.view.reader.components.document.DocumentReader
import note.reader.view.reader.components.notes.NoteEditor
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.ui.graphics.Color

@Composable
fun TwoPaneView() {
    var isLeftPaneExpanded by remember { mutableStateOf(false) }
    var isRightPaneExpanded by remember { mutableStateOf(false) }
    var leftPaneWeight by remember { mutableStateOf(1f) }
    var rightPaneWeight by remember { mutableStateOf(1f) }

    val iconSize = 32.dp


    Row(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .weight(leftPaneWeight)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            DocumentReader()
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 4.dp)
                .width(48.dp)
        ) {
            if (!isRightPaneExpanded) {
                if (!isLeftPaneExpanded) {
                    Button(
                        onClick = {
                            isLeftPaneExpanded = true
                            isRightPaneExpanded = false
                            leftPaneWeight = 2f
                            rightPaneWeight = 0.01f
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Expand Left Pane",
                            tint = Color.White,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            isLeftPaneExpanded = false
                            leftPaneWeight = 1f
                            rightPaneWeight = 1f
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Reset Pane",
                            tint = Color.White,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }

            if (!isLeftPaneExpanded) {
                if (!isRightPaneExpanded) {
                    Button(
                        onClick = {
                            isRightPaneExpanded = true
                            isLeftPaneExpanded = false
                            leftPaneWeight = 0.01f
                            rightPaneWeight = 2f
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Expand Right Pane",
                            tint = Color.White,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            isRightPaneExpanded = false
                            leftPaneWeight = 1f
                            rightPaneWeight = 1f
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Reset Pane",
                            tint = Color.White,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .weight(rightPaneWeight)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            NoteEditor()
        }
    }
}