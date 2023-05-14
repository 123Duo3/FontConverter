package ink.duo3.fontconverter

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
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
        AboutDialog(openDialog = openDialog)
        if (LocalConfiguration.current.screenWidthDp.dp < 600.dp) {
            PortraitLayout(openDialog = openDialog)
        } else {
            LandscapeLayout(openDialog = openDialog)
        }
    }
}

@Composable
fun PortraitLayout(openDialog: MutableState<Boolean>) {
    var input by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val bottomPadding = if(
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() == 0.dp
    ) {
        16.dp
    } else {
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                start = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateStartPadding(LocalLayoutDirection.current),
                top = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateTopPadding(),
                bottom = 0.dp,
                end = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateEndPadding(LocalLayoutDirection.current)
            )
    ) {
        AppHeader(openDialog = openDialog)
        Column(
            Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp / 2)
        ) {
            InputCard(value = input, onValueChange = {input = it}, onClick = { input = ""})
        }
        Column(Modifier.verticalScroll(scrollState)) {
            Spacer(modifier = Modifier.height(8.dp))

            ResultDisplay(input = input)

            Spacer(
                modifier = Modifier.height(bottomPadding)
            )
        }
    }
}

@Composable
fun LandscapeLayout(openDialog: MutableState<Boolean>) {
    var input by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val bottomPadding = if(
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() == 0.dp
    ) {
        16.dp
    } else {
        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    }
    val inputCardModifier = if (LocalConfiguration.current.screenHeightDp.dp < 480.dp) {
        Modifier
            .fillMaxHeight()
            .padding(bottom = bottomPadding)
    } else if (LocalConfiguration.current.screenHeightDp.dp < 900.dp) {
        Modifier.defaultMinSize(minHeight = LocalConfiguration.current.screenHeightDp.dp / 2)
    } else {
        Modifier.defaultMinSize(minHeight = LocalConfiguration.current.screenHeightDp.dp / 3)
    }

    Row(
        Modifier
            .fillMaxSize()
            .padding(
                start = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateStartPadding(LocalLayoutDirection.current),
                top = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateTopPadding(),
                bottom = 0.dp,
                end = WindowInsets.systemBars
                    .asPaddingValues()
                    .calculateEndPadding(LocalLayoutDirection.current)
            )
    ) {
        Column(Modifier.weight(0.5f)) {
            AppHeader(openDialog = openDialog)
            Column(
                Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                InputCard(
                    value = input,
                    onValueChange = {input = it},
                    onClick = { input = ""},
                    modifier = inputCardModifier
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier
                .weight(0.5f)
                .verticalScroll(scrollState)) {
            ResultDisplay(input = input)
            Spacer(modifier = Modifier.height(bottomPadding))
        }
    }
}

@Composable
fun AppHeader(openDialog: MutableState<Boolean>) {
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
}

@Composable
fun ResultDisplay(input: String) {
    Column {
        TextStyle.values().forEach { style ->
            StyleItem(text = input, style = style)
            if (style != TextStyle.values().last()) {
                Divider()
            }
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
