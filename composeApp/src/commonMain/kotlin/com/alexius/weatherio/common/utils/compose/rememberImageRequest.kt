package com.alexius.weatherio.common.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Size
import com.alexius.weatherio.common.extension.compose.dpToPx

@Composable
fun rememberImageRequest(
    imageSize: DpSize? = null,
    url: String
): ImageRequest {
    val size = imageSize?.let{ Size(imageSize.width.dpToPx().toInt(), imageSize.height.dpToPx().toInt()) }
    val context = LocalPlatformContext.current

    return remember(context, url) {
        ImageRequest.Builder(context)
            .crossfade(true)
            .data(url)
            .diskCachePolicy(CachePolicy.ENABLED)
            .run { size?.let{ size(it)} ?: this }
            .build()
    }
}