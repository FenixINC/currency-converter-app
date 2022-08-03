package com.example.currency_converter_app.data.network.factory

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val REQUEST_TIMEOUT_MILLIS: Long = 15000L
private const val CONNECTION_TIMEOUT_MILLIS: Long = 15000L
private const val SOCKET_TIMEOUT_MILLIS: Long = 15000L

object HttpClientFactory {
    fun create(json: Json): HttpClient {
        return HttpClient(engineFactory = Android) {
            install(plugin = ContentNegotiation) {
                json(json = json)
            }
            install(plugin = Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.v(message = "Ktor", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(plugin = HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
                connectTimeoutMillis = CONNECTION_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
            }

            defaultRequest {
                url { protocol = URLProtocol.HTTPS }
                accept(contentType = ContentType.Application.Json)
            }
        }
    }
}