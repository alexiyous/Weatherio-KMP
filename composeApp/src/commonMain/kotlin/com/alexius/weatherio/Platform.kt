package com.alexius.weatherio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform