import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser
import org.intellij.markdown.html.HtmlGenerator
import androidx.compose.ui.Modifier



@Composable
fun HtmlScreen(markdown: String, onBack: () -> Unit) {
    val flavour = CommonMarkFlavourDescriptor()
    val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)
    val htmlContent = HtmlGenerator(markdown, parsedTree, flavour).generateHtml()

    Column {
        Button(
            onClick = { onBack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Back to Editor", color = Color.White)
        }
        HtmlView(
            html = htmlContent,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
