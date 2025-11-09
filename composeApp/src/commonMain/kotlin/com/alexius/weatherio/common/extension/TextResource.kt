package com.alexius.weatherio.common.extension

import androidx.compose.runtime.Composable
import coil3.PlatformContext
import com.alexius.weatherio.utils.TextResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextResource.asString(): String {
    return when (this) {
        is TextResource.PlainText -> text
        is TextResource.ComposePluralResource -> {
            if (formatArgs.isNotEmpty()) {
                pluralStringResource(resId, quantity, *formatArgs.toTypedArray())
            } else {
                pluralStringResource(resId, quantity)
            }
        }
        is TextResource.ComposeStringResource -> {
            if (formatArgs.isNotEmpty()) {
                stringResource(resId, *formatArgs.toTypedArray())
            } else {
                stringResource(resId)
            }
        }
    }
}

expect fun TextResource.asString(context: PlatformContext): String
