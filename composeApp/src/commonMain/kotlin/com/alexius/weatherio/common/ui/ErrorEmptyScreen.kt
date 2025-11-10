package com.alexius.weatherio.common.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.MinorCrash
import androidx.compose.material.icons.filled.MobiledataOff
import androidx.compose.material.icons.filled.NetworkLocked
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.alexius.weatherio.common.extension.asStringCompose
import com.alexius.weatherio.presentation.utils.NavigationType
import com.alexius.weatherio.utils.AppError
import com.alexius.weatherio.utils.TextResource
import weatherio.composeapp.generated.resources.Res
import weatherio.composeapp.generated.resources.error_critical_message
import weatherio.composeapp.generated.resources.error_critical_title
import weatherio.composeapp.generated.resources.error_network_message
import weatherio.composeapp.generated.resources.error_network_title
import weatherio.composeapp.generated.resources.retry

data class ErrorEmptyState(
    val icon: ImageVector,
    val title: TextResource,
    val message: TextResource,
    val buttonText: TextResource?
)

@Composable
fun DatingErrorEmptyState(
    modifier: Modifier = Modifier,
    errorEmptyState: ErrorEmptyState,
    navigationType: NavigationType,
    buttonAction: (() -> Unit)
) {
    EmptyContent(
        modifier = modifier,
        title = errorEmptyState.title.asStringCompose(),
        subtitle = errorEmptyState.message.asStringCompose(),
        image = errorEmptyState.icon,
        buttonText = errorEmptyState.buttonText?.asStringCompose(),
        buttonAction = buttonAction,
        navigationType = navigationType
    )
}

fun AppError.toErrorEmptyState(
    customTitle: TextResource? = null,
    customMessage: TextResource? = null,
    customNetworkErrorIcon: ImageVector? = null,
) : ErrorEmptyState {
    return when(this) {
        is AppError.NetworkError -> ErrorEmptyState(
            icon = Icons.Default.MobiledataOff,
            title = customTitle ?: TextResource.SingleStringResource(Res.string.error_network_title),
            message = customMessage ?: TextResource.SingleStringResource(Res.string.error_network_message),
            buttonText = TextResource.SingleStringResource(Res.string.retry)
        )
        is AppError.ServerError -> ErrorEmptyState(
            icon = if (this.httpCode == 500) Icons.Default.ErrorOutline
             else Icons.Default.NetworkLocked,
            title = TextResource.PlainText("${this.httpCode}"),
            message = TextResource.PlainText(this.message),
            buttonText = if (this.httpCode == 500) {
                TextResource.SingleStringResource(Res.string.retry)
            } else {
                null
            }
        )
        is AppError.UnexpectedError -> ErrorEmptyState(
            icon = Icons.Default.MinorCrash,
            title = TextResource.SingleStringResource(Res.string.error_critical_title),
            message = TextResource.SingleStringResource(Res.string.error_critical_message),
            buttonText = TextResource.SingleStringResource(Res.string.retry)
        )
    }
}
