import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import javax.swing.JEditorPane
import androidx.compose.runtime.remember


@Composable
actual fun HtmlView(html: String, modifier: Modifier) {
    val component = remember(html) {
        JEditorPane("text/html", html).apply {
            isEditable = false
        }
    }

    SwingPanel(
        factory = { component },
        modifier = modifier
    )
}