package com.alexius.weatherio.utils

import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource

sealed class TextResource {

    data class PlainText(val text: String) : TextResource()

    data class ComposeStringResource(
        val resId: StringResource,
        val formatArgs: List<String> = emptyList(),
    ) : TextResource() {
        constructor(
            resId: StringResource,
            vararg formatArgs: String,
        ) : this(resId = resId, formatArgs = formatArgs.toList())
    }

    data class ComposePluralResource(
        val resId: PluralStringResource,
        val quantity: Int,
        val formatArgs: List<Int> = emptyList(),
    ) : TextResource() {
        constructor(
            resId: PluralStringResource,
            quantity: Int,
            vararg formatArgs: Int,
        ) : this(
            resId = resId,
            quantity = quantity,
            formatArgs = formatArgs.toList(),
        )
    }
}