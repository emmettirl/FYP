package note.reader.view.components.menuBar

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import note.reader.controller.ProgramStateSingleton
import note.reader.model.enums.Layouts
import note.reader.view.helper_functions.titleCase


// Composable function, to decide on how to display the menu button
@Composable
fun MenuButton(modifier: Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = Color.White
        )
    ) {
        content()
    }
}

// overloaded MenuButton function, to display a button without any content
@Composable
fun MenuButton(modifier: Modifier) {
    Button(
        onClick = { },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Gray,
        contentColor = Color.White
    )) {
        Text("")
    }
}

// Enum to declare the different menu buttons
enum class MenuButtonEnum {
    HOME,
    THEME
}

// Data class to store the content and onClick function for each menu button
data class MenuButtonData(
    val content: @Composable () -> Unit,
    val onClick: () -> Unit
)

// Map to store the content and onClick function for each menu button
val menuButtonLambdaMap: Map<MenuButtonEnum, MenuButtonData> = mapOf(
    MenuButtonEnum.HOME to MenuButtonData(
        // Content of the button, this is a composable function for the text
        content = {
            if (ProgramStateSingleton.instance.currentLayout == Layouts.READER) {
                Text("To ${titleCase(Layouts.HOME.toString())}")
            } else if (ProgramStateSingleton.instance.currentLayout == Layouts.HOME) {
                Text("To ${titleCase(Layouts.READER.toString())}")
            }
        },

        // onClick functionality
        onClick = {
            if (ProgramStateSingleton.instance.currentLayout == Layouts.READER) {
                ProgramStateSingleton.instance.currentLayout = Layouts.HOME
            } else if (ProgramStateSingleton.instance.currentLayout == Layouts.HOME) {
                ProgramStateSingleton.instance.currentLayout = Layouts.READER
            }
        }
    ),
    MenuButtonEnum.THEME to MenuButtonData(
        content = {
            Text("Theme")
        },
        onClick = {
            ProgramStateSingleton.instance.isDarkTheme =
                !ProgramStateSingleton.instance.isDarkTheme
        }
    )

)


