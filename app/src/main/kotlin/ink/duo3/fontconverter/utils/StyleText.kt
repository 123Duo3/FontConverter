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
        // Capitals of Greek Alphabet
        'Α' -> "\uD835\uDEA8"
        'Β' -> "\uD835\uDEA9"
        'Γ' -> "\uD835\uDEAA"
        'Δ' -> "\uD835\uDEAB"
        'Ε' -> "\uD835\uDEAC"
        'Ζ' -> "\uD835\uDEAD"
        'Η' -> "\uD835\uDEAE"
        'Θ' -> "\uD835\uDEAF"
        'Ι' -> "\uD835\uDEB0"
        'Κ' -> "\uD835\uDEB1"
        'Λ' -> "\uD835\uDEB2"
        'Μ' -> "\uD835\uDEB3"
        'Ν' -> "\uD835\uDEB4"
        'Ξ' -> "\uD835\uDEB5"
        'Ο' -> "\uD835\uDEB6"
        'Π' -> "\uD835\uDEB7"
        'Ρ' -> "\uD835\uDEB8"
        'ϴ' -> "\uD835\uDEB9"
        'Σ' -> "\uD835\uDEBA"
        'Τ' -> "\uD835\uDEBB"
        'Υ' -> "\uD835\uDEBC"
        'Φ' -> "\uD835\uDEBD"
        'Χ' -> "\uD835\uDEBE"
        'Ψ' -> "\uD835\uDEBF"
        'Ω' -> "\uD835\uDEC0"
        '∇' -> "\uD835\uDEC1"
        // Lowercase of Greek Alphabet
        'α' -> "\uD835\uDEC2"
        'β' -> "\uD835\uDEC3"
        'γ' -> "\uD835\uDEC4"
        'δ' -> "\uD835\uDEC5"
        'ε' -> "\uD835\uDEC6"
        'ζ' -> "\uD835\uDEC7"
        'η' -> "\uD835\uDEC8"
        'θ' -> "\uD835\uDEC9"
        'ι' -> "\uD835\uDECA"
        'κ' -> "\uD835\uDECB"
        'λ' -> "\uD835\uDECC"
        'μ' -> "\uD835\uDECD"
        'ν' -> "\uD835\uDECE"
        'ξ' -> "\uD835\uDECF"
        'ο' -> "\uD835\uDED0"
        'π' -> "\uD835\uDED1"
        'ρ' -> "\uD835\uDED2"
        'ς' -> "\uD835\uDED3"
        'σ' -> "\uD835\uDED4"
        'τ' -> "\uD835\uDED5"
        'υ' -> "\uD835\uDED6"
        'φ' -> "\uD835\uDED7"
        'χ' -> "\uD835\uDED8"
        'ψ' -> "\uD835\uDED9"
        'ω' -> "\uD835\uDEDA"
        '∂' -> "\uD835\uDEDB"
        'ϵ' -> "\uD835\uDEDC"
        'ϑ' -> "\uD835\uDEDD"
        'ϰ' -> "\uD835\uDEDE"
        'ϕ' -> "\uD835\uDEDF"
        'ϱ' -> "\uD835\uDEE0"
        'ϖ' -> "\uD835\uDEE1"

        'Ϝ' -> "\uD835\uDFCA"
        'ϝ' -> "\uD835\uDFCB"

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
