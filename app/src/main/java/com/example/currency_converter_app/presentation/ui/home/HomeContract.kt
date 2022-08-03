package com.example.currency_converter_app.presentation.ui.home

import com.example.currency_converter_app.data.network.response.CurrencyModel
import com.example.currency_converter_app.presentation.base.ViewEffect
import com.example.currency_converter_app.presentation.base.ViewEvent
import com.example.currency_converter_app.presentation.base.ViewState

sealed class HomeEvent : ViewEvent {

    object CloseApp : HomeEvent()
    object OpenHistoryScreen : HomeEvent()

    object LoadAllCurrencies : HomeEvent()
    data class ConvertCurrencies(
        val fromCurrency: String,
        val toCurrency: String
    ) : HomeEvent()

    data class SaveDate(val date: String) : HomeEvent()
    data class SaveFromCurrency(val currency: String) : HomeEvent()
    data class SaveToCurrency(val currency: String) : HomeEvent()
    data class SaveAmount(val amount: Double) : HomeEvent()
}

data class HomeState(
    val currencies: List<CurrencyModel> = emptyList(),
    val convertResult: String = "",
) : ViewState

sealed class HomeEffect : ViewEffect {
    data class ShowError(val message: String) : HomeEffect()
}