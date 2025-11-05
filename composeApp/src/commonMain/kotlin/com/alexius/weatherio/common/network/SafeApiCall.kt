package com.alexius.weatherio.common.network

import com.alexius.weatherio.utils.AppError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

suspend inline fun <reified ResponseType> HttpClient.safeApiCall(
    builder: HttpRequestBuilder.() -> Unit,
) = safeApiCall<ResponseType, ResponseType>(
    execute = { this.request(builder) },
    mapToDomain = { it }
)

suspend inline fun <reified ResponseType, ReturnType> HttpClient.safeApiCall(
    mapper: ResponseType.() -> ReturnType,
    builder: HttpRequestBuilder.() -> Unit,
) = safeApiCall<ResponseType, ReturnType>(
    execute = { this.request(builder) },
    mapToDomain = mapper,
)

suspend inline fun <reified DTO, DM> safeApiCall(
    execute: () -> HttpResponse,
    mapToDomain: (DTO) -> DM,
): Result<DM> =
    runCatching {
        val response = execute.invoke()
        handleResponse(response, mapToDomain)
    }.fold(
        onSuccess = { Result.success(it) },
        onFailure = { handleFailure(it) },
    )

suspend inline fun <reified DTO, DM> handleResponse(
    response: HttpResponse,
    mapToDomain: (DTO) -> DM,
): DM {
    return if (response.status.isSuccess()) {
        mapToDomain(response.body<DTO>())
    } else {
        val errorBody = response.bodyAsText()
        throw ApiException(
            httpCode = response.status.value,
            errorMsg = errorBody
        )
    }
}

fun handleFailure(throwable: Throwable): Result<Nothing> {
    throwable.printStackTrace()
    val appError = when (throwable) {
        is ApiException -> {
            if (throwable.httpCode == HttpStatusCode.InternalServerError.value && throwable.errorMsg.isEmpty()) {
                AppError.UnexpectedError(
                    message = "",
                    cause = throwable
                )
            } else {
                AppError.ServerError(
                    httpCode = throwable.httpCode,
                    message = "Server error with code ${throwable.httpCode}",
                )
            }
        }
        is ConnectTimeoutException,
        is SocketTimeoutException,
        is IOException,
        is JsonConvertException,
        is SerializationException -> AppError.NetworkError("Network Error", throwable)
        else -> AppError.UnexpectedError("Unexpected Error", throwable)
    }
    return Result.failure(appError)
}