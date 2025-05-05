package note.reader.theme

import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

private val LightColorPalette = lightColors(
    primary = ColorScheme.Primary,
    primaryVariant = ColorScheme.PrimaryVariant,
    secondary = ColorScheme.Secondary,
    background = ColorScheme.Background,
    surface = ColorScheme.Surface,
    error = ColorScheme.Error,
    onPrimary = ColorScheme.OnPrimary,
    onSecondary = ColorScheme.OnSecondary,
    onBackground = ColorScheme.OnBackground,
    onSurface = ColorScheme.OnSurface,
    onError = ColorScheme.OnError
)

private val DarkColorPalette = darkColors(
    primary = ColorSchemeDark.Primary,
    primaryVariant = ColorSchemeDark.PrimaryVariant,
    secondary = ColorSchemeDark.Secondary,
    background = ColorSchemeDark.Background,
    surface = ColorSchemeDark.Surface,
    error = ColorSchemeDark.Error,
    onPrimary = ColorSchemeDark.OnPrimary,
    onSecondary = ColorSchemeDark.OnSecondary,
    onBackground = ColorSchemeDark.OnBackground,
    onSurface = ColorSchemeDark.OnSurface,
    onError = ColorSchemeDark.OnError
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false, // You can toggle this based on system settings
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}