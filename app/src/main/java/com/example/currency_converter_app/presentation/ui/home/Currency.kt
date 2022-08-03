package com.example.currency_converter_app.presentation.ui.home

data class Currency(
    val date: Int,
    val fromCurrency: String,
    val toCurrency: String,
    val amount: Double
)