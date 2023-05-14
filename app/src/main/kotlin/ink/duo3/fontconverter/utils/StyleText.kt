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
                TextStyle.SANS_NORMAL -> c.toSansNormal()
                TextStyle.SANS_BOLD -> c.toSansBold()
                TextStyle.SANS_ITALIC -> c.toSansItalic()
                TextStyle.SANS_BOLD_ITALIC -> c.toSansBoldItalic()
                TextStyle.SCRIPT_NORMAL -> c.toScriptNormal()
                TextStyle.SCRIPT_BOLD -> c.toScriptBold()
                TextStyle.FRAKTUR_NORMAL -> c.toFrakturNormal()
                TextStyle.FRAKTUR_BOLD -> c.toFrakturBold()
                TextStyle.MONOSPACE_NORMAL -> c.toMonospaceNormal()
                TextStyle.DOUBLESTRUCK_BOLD -> c.toDoublestruckBold()
            }
        )
    }
    return sb.toString()
}

enum class TextStyle {
    BOLD,
    ITALIC,
    BOLD_ITALIC,
    SANS_NORMAL,
    SANS_BOLD,
    SANS_ITALIC,
    SANS_BOLD_ITALIC,
    SCRIPT_NORMAL,
    SCRIPT_BOLD,
    FRAKTUR_NORMAL,
    FRAKTUR_BOLD,
    MONOSPACE_NORMAL,
    DOUBLESTRUCK_BOLD
}

@Composable
fun TextStyle.toName(): String {
    return when (this) {
        TextStyle.BOLD -> stringResource(id = R.string.font_bold)
        TextStyle.ITALIC -> stringResource(id = R.string.font_italic)
        TextStyle.BOLD_ITALIC -> stringResource(id = R.string.font_bold_italic)
        TextStyle.SANS_NORMAL -> stringResource(id = R.string.font_sans_normal)
        TextStyle.SANS_BOLD -> stringResource(id = R.string.font_sans_bold)
        TextStyle.SANS_ITALIC -> stringResource(id = R.string.font_sans_italic)
        TextStyle.SANS_BOLD_ITALIC -> stringResource(id = R.string.font_sans_bold_italic)
        TextStyle.SCRIPT_NORMAL -> stringResource(id = R.string.font_script_normal)
        TextStyle.SCRIPT_BOLD -> stringResource(id = R.string.font_script_bold)
        TextStyle.FRAKTUR_NORMAL -> stringResource(id = R.string.font_fraktur_normal)
        TextStyle.FRAKTUR_BOLD -> stringResource(id = R.string.font_fraktur_bold)
        TextStyle.MONOSPACE_NORMAL -> stringResource(id = R.string.font_monospace_normal)
        TextStyle.DOUBLESTRUCK_BOLD -> stringResource(id = R.string.font_doublestruck_bold)
    }
}

fun Char.toBold(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC00).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDC1A).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFCE).toChar()}"
        // Capitals of Greek Alphabet
        'ϴ' -> "\uD835\uDEB9"
        in 'Α'..'Ω' -> "\uD835${(this.code - 'Α'.code + 0xDEA8).toChar()}"
        '∇' -> "\uD835\uDEC1"
        // Lowercase of Greek Alphabet
        in 'α'..'ω' -> "\uD835${(this.code - 'α'.code + 0xDEC2).toChar()}"
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
        // Capitals of Greek Alphabet
        'ϴ' -> "\uD835\uDEF3"
        in 'Α'..'Ω' -> "\uD835${(this.code - 'Α'.code + 0xDEE2).toChar()}"
        '∇' -> "\uD835\uDEFB"
        // Lowercase of Greek Alphabet
        in 'α'..'ω' -> "\uD835${(this.code - 'α'.code + 0xDEFC).toChar()}"
        '∂' -> "\uD835\uDF15"
        'ϵ' -> "\uD835\uDF16"
        'ϑ' -> "\uD835\uDF17"
        'ϰ' -> "\uD835\uDF18"
        'ϕ' -> "\uD835\uDF19"
        'ϱ' -> "\uD835\uDF1A"
        'ϖ' -> "\uD835\uDF1B"
        else -> this.toString()
    }
}

fun Char.toBoldItalic(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC68).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDC82).toChar()}"
        // Capitals of Greek Alphabet
        'ϴ' -> "\uD835\uDF2D"
        in 'Α'..'Ω' -> "\uD835${(this.code - 'Α'.code + 0xDF1C).toChar()}"
        '∇' -> "\uD835\uDF35"
        // Lowercase of Greek Alphabet
        in 'α'..'ω' -> "\uD835${(this.code - 'α'.code + 0xDF36).toChar()}"
        '∂' -> "\uD835\uDF4F"
        'ϵ' -> "\uD835\uDF50"
        'ϑ' -> "\uD835\uDF51"
        'ϰ' -> "\uD835\uDF52"
        'ϕ' -> "\uD835\uDF53"
        'ϱ' -> "\uD835\uDF54"
        'ϖ' -> "\uD835\uDF55"
        else -> this.toString()
    }
}

fun Char.toSansNormal(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDDA0).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDDBA).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFE2).toChar()}"
        else -> this.toString()
    }
}
fun Char.toSansBold(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDDD4).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDDEE).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFEC).toChar()}"
        // Capitals of Greek Alphabet
        'ϴ' -> "\uD835\uDF67"
        in 'Α'..'Ω' -> "\uD835${(this.code - 'Α'.code + 0xDF56).toChar()}"
        '∇' -> "\uD835\uDF6F"
        // Lowercase of Greek Alphabet
        in 'α'..'ω' -> "\uD835${(this.code - 'α'.code + 0xDF70).toChar()}"
        '∂' -> "\uD835\uDF89"
        'ϵ' -> "\uD835\uDF8A"
        'ϑ' -> "\uD835\uDF8B"
        'ϰ' -> "\uD835\uDF8C"
        'ϕ' -> "\uD835\uDF8D"
        'ϱ' -> "\uD835\uDF8E"
        'ϖ' -> "\uD835\uDF8F"

        else -> this.toString()
    }
}
fun Char.toSansItalic(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDE08).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDE22).toChar()}"
        else -> this.toString()
    }
}
fun Char.toSansBoldItalic(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDE3C).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDE56).toChar()}"
        // Capitals of Greek Alphabet
        'ϴ' -> "\uD835\uDFA1"
        in 'Α'..'Ω' -> "\uD835${(this.code - 'Α'.code + 0xDF90).toChar()}"
        '∇' -> "\uD835\uDFA9"
        // Lowercase of Greek Alphabet
        in 'α'..'ω' -> "\uD835${(this.code - 'α'.code + 0xDFAA).toChar()}"
        '∂' -> "\uD835\uDFC3"
        'ϵ' -> "\uD835\uDFC4"
        'ϑ' -> "\uD835\uDFC5"
        'ϰ' -> "\uD835\uDFC6"
        'ϕ' -> "\uD835\uDFC7"
        'ϱ' -> "\uD835\uDFC8"
        'ϖ' -> "\uD835\uDFC9"

        else -> this.toString()
    }
}

fun Char.toScriptNormal(): String {
    return when (this) {
        'B' -> "\u212C"
        'E' -> "\u2130"
        'F' -> "\u2131"
        'H' -> "\u210B"
        'I' -> "\u2110"
        'L' -> "\u2112"
        'M' -> "\u2133"
        'R' -> "\u211B"
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDC9C).toChar()}"
        'e' -> "\u212F"
        'g' -> "\u210A"
        'o' -> "\u2134"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDCB6).toChar()}"
        else -> this.toString()
    }
}

fun Char.toScriptBold(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDCD0).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDCEA).toChar()}"
        else -> this.toString()
    }
}

fun Char.toFrakturNormal(): String {
    return when (this) {
        'C' -> "\u212D"
        'H' -> "\u210C"
        'I' -> "\u2111"
        'R' -> "\u211C"
        'Z' -> "\u2128"
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDD04).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDD1E).toChar()}"
        else -> this.toString()
    }
}

fun Char.toFrakturBold(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDD6C).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDD86).toChar()}"
        else -> this.toString()
    }
}

fun Char.toMonospaceNormal(): String {
    return when (this) {
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDE70).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDE8A).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFF6).toChar()}"
        else -> this.toString()
    }
}

fun Char.toDoublestruckBold(): String {
    return when (this) {
        'C' -> "\u2102"
        'H' -> "\u210D"
        'N' -> "\u2115"
        'P' -> "\u2119"
        'Q' -> "\u211A"
        'R' -> "\u211D"
        'Z' -> "\u2124"
        in 'A'..'Z' -> "\uD835${(this.code - 'A'.code + 0xDD38).toChar()}"
        in 'a'..'z' -> "\uD835${(this.code - 'a'.code + 0xDD52).toChar()}"
        in '0'..'9' -> "\uD835${(this.code - '0'.code + 0xDFD8).toChar()}"
        else -> this.toString()
    }
}
