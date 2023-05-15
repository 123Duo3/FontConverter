package ink.duo3.fontconverter.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ink.duo3.fontconverter.R
import ink.duo3.fontconverter.utils.TextStyle
import ink.duo3.fontconverter.utils.toName
import ink.duo3.fontconverter.utils.toStyled

@Composable
fun StyleItem(text: String, style: TextStyle) {
    val styledText = text.toStyled(style)
    val styleName = style.toName()
    StyledItemDisplay(tag = styleName, text = styledText)
}

@Composable
fun StyledItemDisplay (tag: String, text: String) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val copiedToast = stringResource(id = R.string.toast_copied)
    Column(Modifier.padding(16.dp, 0.dp)) {
        Row(
            Modifier
                .padding(16.dp, 4.dp, 4.dp, 0.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tag.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 0.dp)
            )
            Spacer(Modifier.weight(1f))

            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = Color.Transparent
            ) {
                Icon(
                    modifier = if (text.isNotEmpty()) {
                        Modifier
                            .clickable(
                                onClick = {
                                    clipboardManager
                                        .setText(AnnotatedString(text))
                                        .also {
                                            Toast
                                                .makeText(
                                                    context,
                                                    copiedToast,
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
                                },
                                onClickLabel = "Copy"
                            )
                            .padding(8.dp)
                    } else {
                        Modifier
                            .padding(8.dp)
                    },
                    painter = painterResource(id = R.drawable.ic_content_copy_24dp),
                    contentDescription = "Copy",
                    tint = if (text.isNotEmpty()) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                    }
                )
            }

        }
//            if (styledText.isNotEmpty()) {
        SelectionContainer {
            Text(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                    .fillMaxWidth(),
                text = text,
                style = MaterialTheme.typography.headlineMedium
            )
        }
//            } else {
//                Text(
//                    modifier = Modifier
//                        .padding(16.dp, 0.dp, 16.dp, 16.dp)
//                        .fillMaxWidth(),
//                    text = stringResource(id = R.string.styled_hint).toStyled(style),
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
//                )
//            }
    }
}


@Preview
@Composable
fun StyleItemPreview() {
    StyleItem(text = "Hello World 123", style = TextStyle.ITALIC)
}