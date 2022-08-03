package com.example.currency_converter_app.data.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryModel(
    @SerialName(value = "id")
    val id: String,

    @SerialName(value = "date")
    val date: String,

    @SerialName(value = "originalCurrency")
    val originalCurrency: String,

    @SerialName(value = "targetCurrency")
    val targetCurrency: String,

    @SerialName(value = "amount")
    val amount: String,

    @SerialName(value = "result")
    val result: String
)