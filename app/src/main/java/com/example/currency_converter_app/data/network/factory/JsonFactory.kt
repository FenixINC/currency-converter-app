package com.example.currency_converter_app.data.network.factory

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object JsonFactory {
    fun create(modules: List<SerializersModule> = emptyList()): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            serializersModule = SerializersModule {
                modules.forEach { include(it) }
            }
        }
    }
}