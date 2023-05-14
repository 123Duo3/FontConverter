package ink.duo3.fontconverter.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import ink.duo3.fontconverter.R

@Composable
fun InputCard (
    value: String,
    onValueChange: (newValue: String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column() {
            Row(
                Modifier
                    .padding(16.dp, 4.dp, 4.dp, 0.dp)
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.font_original).uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                    modifier = Modifier.padding(top = 0.dp)
                )
                Spacer(Modifier.weight(1f))
                if (value.isNotEmpty()) {
                    Surface(
                        modifier = Modifier,
                        shape = CircleShape,
                        color = Color.Transparent
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable(onClick = onClick, onClickLabel = "Clear")
                                .padding(8.dp),
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }

            val customTextSelectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.primaryContainer,
                backgroundColor = MaterialTheme.colorScheme.onPrimary.copy(0.2f)
            )

            CompositionLocalProvider(
                LocalTextSelectionColors provides customTextSelectionColors
            ) {
                BasicTextField(
                    modifier = Modifier
                        .padding(16.dp, 0.dp, 16.dp, 16.dp)
                        .defaultMinSize(minHeight = 128.dp)
                        .fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = false,
                    textStyle = MaterialTheme.typography.headlineMedium
                        .copy(color = MaterialTheme.colorScheme.onPrimary),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary),
                    decorationBox = {
                        Box(
                            modifier = Modifier
                                .weight(1F),
                            contentAlignment = Alignment.TopStart
                        ) {
                            it()
                            if (value.isEmpty()) Text(
                                stringResource(id = R.string.input_hint),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun InputCardPreview() {
    var input = ""
    InputCard(input, { input = it }, {})
}