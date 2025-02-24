package note.reader.view.components.menuBar

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import note.reader.controller.ProgramState
import note.reader.model.enums.Layouts
import note.reader.view.helper_functions.titleCase

@Composable
fun MenuButton(modifier: Modifier) {
    Button(
        onClick = {
            if (ProgramState.currentLayout == Layouts.READER) {
                ProgramState.currentLayout = Layouts.HOME
            } else if (ProgramState.currentLayout == Layouts.HOME) {
                ProgramState.currentLayout = Layouts.READER
            }
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        if (ProgramState.currentLayout == Layouts.READER) {
            Text("To ${titleCase(Layouts.HOME.toString())}")
        } else if (ProgramState.currentLayout == Layouts.HOME) {
            Text("To ${titleCase(Layouts.READER.toString())}")

        }
    }
}