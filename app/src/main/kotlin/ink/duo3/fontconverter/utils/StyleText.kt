package ink.duo3.fontconverter.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ink.duo3.fontconverter.R

fun String.toStyled(style: TextStyle): String {
    val sb = StringBuilder()
    for (c in this) {
        sb.append(
            when (style) {
                TextStyle.BOLD -> c.toBold()
                TextStyle.ITALIC -> c.toItalic()
                TextStyle.BOLD_ITALIC -> c.toBoldItalic()
                else -> c
            }
        )
    }
    return sb.toString()
}

enum class TextStyle {
    BOLD,
    ITALIC,
    BOLD_ITALIC,
}

@Composable
fun TextStyle.toName(): String {
    return when (this) {
        TextStyle.BOLD -> stringResource(id = R.string.font_bold)
        TextStyle.ITALIC -> stringResource(id = R.string.font_italic)
        TextStyle.BOLD_ITALIC -> stringResource(id = R.string.font_bold_italic)
    }
}

fun Char.toBold(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC00).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDC1A).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFCE).toChar()}"
        else -> this.toString()
    }
}

fun Char.toItalic(): String {
    return when (this) {
        'h' -> "\u210E"
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC34).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDC4E).toChar()}"
        else -> this.toString()
    }
}

fun Char.toBoldItalic(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC68).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDC82).toChar()}"
        else -> this.toString()
    }
}
