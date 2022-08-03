package com.example.currency_converter_app.data.repository

import com.example.currency_converter_app.data.database.CurrencyLocalDataSource
import com.example.currency_converter_app.data.database.HistoryModel
import com.example.currency_converter_app.data.network.response.CurrencyModel
import com.example.currency_converter_app.data.network.source.CurrencyRemoteDataSource
import com.example.currency_converter_app.domain.repository.CurrencyRepository
import com.example.currency_converter_app.extention.formatNumber
import com.example.currency_converter_app.presentation.ui.home.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

private const val UAH = "UAH"

class CurrencyRepositoryImpl(
    private val currencyRemoteDataSource: CurrencyRemoteDataSource,
    private val currencyLocalDataSource: CurrencyLocalDataSource
) : CurrencyRepository {

    private val _currencyFlow = MutableStateFlow(value = emptyList<CurrencyModel>())
    override val currencyFlow: StateFlow<List<CurrencyModel>> = _currencyFlow.asStateFlow()

    private val _convertResultFlow = MutableStateFlow(value = 0.0)
    override val convertResultFlow: Flow<Double> = _convertResultFlow.asStateFlow()

    override val historyFlow: Flow<List<HistoryModel>>
        get() = currencyLocalDataSource.getHistory()

    override suspend fun loadCurrency(currency: Currency): Result<Unit> {
        if (!currency.fromCurrency.equals(other = UAH, ignoreCase = true) &&
            currency.toCurrency.equals(other = UAH, ignoreCase = true)
        ) {
            val response = currencyRemoteDataSource.loadCurrency(
                date = currency.date,
                currency = currency.fromCurrency
            )

            if (response.isNotEmpty()) {
                val result = currency.amount * response.first().rate.toDouble()
                saveToHistory(
                    currency = currency,
                    date = response.first().exchangeDate,
                    result = result
                )

                _convertResultFlow.update { result }
            }
        } else if (
            currency.fromCurrency.equals(other = UAH, ignoreCase = true) &&
            !currency.toCurrency.equals(other = UAH, ignoreCase = true)
        ) {
            val response = currencyRemoteDataSource.loadCurrency(
                date = currency.date,
                currency = currency.toCurrency
            )

            if (response.isNotEmpty()) {
                val result = currency.amount / response.first().rate.toDouble()
                saveToHistory(
                    currency = currency,
                    date = response.first().exchangeDate,
                    result = result
                )

                _convertResultFlow.update { result }
            }
        } else {
            val fromCurrencyResponse = currencyRemoteDataSource.loadCurrency(
                date = currency.date,
                currency = currency.fromCurrency
            )
            val toCurrencyResponse = currencyRemoteDataSource.loadCurrency(
                date = currency.date,
                currency = currency.toCurrency
            )


            if (fromCurrencyResponse.isNotEmpty() && toCurrencyResponse.isNotEmpty()) {
                val firstCurrencyRate = fromCurrencyResponse.first().rate.toDouble()
                val secondCurrencyRate = toCurrencyResponse.first().rate.toDouble()

                val result = currency.amount * firstCurrencyRate / secondCurrencyRate

                saveToHistory(
                    currency = currency,
                    date = fromCurrencyResponse.first().exchangeDate,
                    result = result
                )

                _convertResultFlow.update { result }
            }
        }

        return Result.success(value = Unit)
    }

    override suspend fun loadAllCurrencies(): Result<Unit> {
        val result = currencyRemoteDataSource.loadAllCurrencies()
        _currencyFlow.update { result }
        return Result.success(value = Unit)
    }

    private fun saveToHistory(currency: Currency, date: String, result: Double) {
        val list = mutableListOf(
            HistoryModel(
                id = UUID.randomUUID().toString(),
                date = date,
                originalCurrency = currency.fromCurrency,
                targetCurrency = currency.toCurrency,
                amount = currency.amount.toString(),
                result = result.formatNumber()
            )
        )
        currencyLocalDataSource.saveToHistory(list)
    }
}