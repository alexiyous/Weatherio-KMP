package com.alexius.weatherio.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
@JvmInline
value class DrawableResource(val id: Int)

data class ErrorEmptyState(
    @DrawableRes
    val icon: Int,
    val title: TextResource,
    val message: TextResource,
    val buttonText: TextResource?
)

@Composable
fun DatingErrorEmptyState(
    modifier: Modifier = Modifier,
    errorEmptyState: ErrorEmptyState,
    buttonAction: (() -> Unit)
) {
    EmptyContent(
        modifier = modifier,
        title = errorEmptyState.title.asString(),
        subtitle = errorEmptyState.message.asString(),
        image = painterResource(errorEmptyState.icon),
        buttonText = errorEmptyState.buttonText?.asString(),
        buttonAction = buttonAction
    )
}

fun AppError.toErrorEmptyState(
    customTitle: TextResource? = null,
    customMessage: TextResource? = null,
    customNetworkErrorIcon: ((Int) -> DrawableResource?)? = null,
) : ErrorEmptyState {
    return when(this) {
        is AppError.NetworkError -> ErrorEmptyState(
            icon = R.drawable.image_network_error,
            title = customTitle ?: TextResource.StringResource(R.string.error_network_title),
            message = customMessage ?: TextResource.StringResource(R.string.error_network_message),
            buttonText = TextResource.StringResource(R.string.retry)
        )
        is AppError.ServerError -> ErrorEmptyState(
            icon = if (this.httpCode == 500) {
                R.drawable.image_server_error
            } else {
                // Если у нас не 500 ошибка, то кастомная картинка по требованию дизайна
                customNetworkErrorIcon?.invoke(httpCode)?.id ?: R.drawable.image_server_error
            },
            title = TextResource.PlainText(this.title),
            message = TextResource.PlainText(this.message),
            buttonText = if (this.httpCode == 500) {
                TextResource.StringResource(R.string.retry)
            } else {
                // На другие коды ошибок с бекенда, кнопку не показываем
                null
            }
        )
        is AppError.UnexpectedError -> ErrorEmptyState(
            icon = R.drawable.image_danger_error,
            title = TextResource.StringResource(R.string.error_critical_title),
            message = TextResource.StringResource(R.string.error_critical_message),
            buttonText = TextResource.StringResource(R.string.retry)
        )
    }
}*/
