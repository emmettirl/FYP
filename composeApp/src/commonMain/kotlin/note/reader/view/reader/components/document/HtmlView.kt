import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
expect fun HtmlView(html: String, modifier: Modifier)
