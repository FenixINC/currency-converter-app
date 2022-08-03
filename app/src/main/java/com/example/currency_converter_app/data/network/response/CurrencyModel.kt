package com.example.currency_converter_app.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyModel(
    @SerialName(value = "r030")
    val code: String = "",

    @SerialName(value = "rate")
    val rate: String = "",

    @SerialName(value = "txt")
    val description: String = "",

    @SerialName(value = "cc")
    val name: String = "",

    @SerialName(value = "exchangedate")
    val exchangeDate: String = ""
)
