package com.example.currency_converter_app.domain.repository

import com.example.currency_converter_app.data.database.HistoryModel
import com.example.currency_converter_app.data.network.response.CurrencyModel
import com.example.currency_converter_app.presentation.ui.home.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    val currencyFlow: Flow<List<CurrencyModel>>
    val convertResultFlow: Flow<Double>
    val historyFlow: Flow<List<HistoryModel>>

    suspend fun loadCurrency(currency: Currency): Result<Unit>
    suspend fun loadAllCurrencies(): Result<Unit>

}