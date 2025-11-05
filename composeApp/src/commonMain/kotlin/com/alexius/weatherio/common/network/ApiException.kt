package com.alexius.weatherio.common.network

class ApiException(
    val httpCode: Int,
    val errorMsg: String,
) : Exception(errorMsg)