package ink.duo3.fontconverter

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.PackageInfoCompat
import ink.duo3.fontconverter.ui.components.AppBanner
import ink.duo3.fontconverter.ui.components.AppIcon
import ink.duo3.fontconverter.ui.components.InputCard
import ink.duo3.fontconverter.ui.components.StyleItem
import ink.duo3.fontconverter.utils.TextStyle
import ink.duo3.fontconverter.utils.getAppVersion

@Composable
fun FontConverterApp () {
    val openDialog = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var input by remember { mutableStateOf("") }

        AboutDialog(openDialog = openDialog)

        Column(
            Modifier.padding(WindowInsets.systemBars.asPaddingValues())
        ) {
            Row(
                modifier = Modifier.padding(16.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppBanner()
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    modifier = Modifier,
                    shape = CircleShape,
                    color = Color.Transparent
                ){
                    Icon(
                        modifier = Modifier
                            .clickable(onClickLabel = "About") { openDialog.value = true }
                            .padding(16.dp),
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "About",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Column( Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)) {
                InputCard(value = input, onValueChange = {input = it}, onClick = { input = ""})
            }
            StyleItem(text = input, style = TextStyle.BOLD)
            Divider()
            StyleItem(text = input, style = TextStyle.ITALIC)
            Divider()
            StyleItem(text = input, style = TextStyle.BOLD_ITALIC)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutDialog (openDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val versionName = getAppVersion(context)?.versionName
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AppIcon(48.dp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = stringResource(id = R.string.app_name_full),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = stringResource(id = R.string.version)
                                    .format((versionName ?: stringResource(id = R.string.unknown)))
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    }
}
