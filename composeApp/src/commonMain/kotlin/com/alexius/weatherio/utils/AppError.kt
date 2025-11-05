package com.alexius.weatherio.utils

sealed class AppError(
    override val message: String,
    override val cause: Throwable?
) : Exception(message, cause){

    class NetworkError(
        message: String,
        cause: Throwable
    ) : AppError(message, cause)

    class ServerError(
        val httpCode: Int,
        override val message: String
    ) : AppError(message, null)

    class UnexpectedError(
        message: String,
        cause: Throwable?
    ): AppError(message, cause)

}

fun Throwable.toAppError() = (this as? AppError) ?: AppError.UnexpectedError("", this.cause)