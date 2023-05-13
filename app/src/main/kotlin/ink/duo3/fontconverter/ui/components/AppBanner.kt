package ink.duo3.fontconverter.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ink.duo3.fontconverter.R
import ink.duo3.fontconverter.ui.theme.seed

@Composable
fun AppBanner() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon()
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.app_name_full),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun AppIcon(size: Dp = 32.dp) {
    Surface(
        color = seed,
        shape = CircleShape,
        border = BorderStroke(
            0.1.dp,
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f)
        ),
        modifier = Modifier.size(size)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "icon",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.scale(1.4f)
        )
    }
}

@Preview
@Composable
fun AppBannerPreview() {
    AppBanner()
}