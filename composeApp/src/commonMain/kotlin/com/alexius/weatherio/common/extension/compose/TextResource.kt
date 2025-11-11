package com.alexius.weatherio.common.extension.compose

import androidx.compose.runtime.Composable
import com.alexius.weatherio.common.utils.TextResource
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextResource.asStringCompose(): String {
    return when (this) {
        is TextResource.PlainText -> text
        is TextResource.PluralResource -> {
            if (formatArgs.isNotEmpty()) {
                pluralStringResource(resId, quantity, *formatArgs.toTypedArray())
            } else {
                pluralStringResource(resId, quantity)
            }
        }
        is TextResource.SingleStringResource -> {
            if (formatArgs.isNotEmpty()) {
                stringResource(resId, *formatArgs.toTypedArray())
            } else {
                stringResource(resId)
            }
        }
    }
}

suspend fun TextResource.asString(): String {
    return when(this) {
        is TextResource.PlainText -> text
        is TextResource.PluralResource -> getPluralString(resId, quantity)
        is TextResource.SingleStringResource -> getString(resId)
    }
}

