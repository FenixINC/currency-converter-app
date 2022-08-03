package com.example.currency_converter_app.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.example.currency_converter_app.data.network.response.CurrencyModel
import com.example.currency_converter_app.domain.usecase.ConvertCurrencyUseCase
import com.example.currency_converter_app.domain.usecase.GetCurrencyConvertResultUseCase
import com.example.currency_converter_app.domain.usecase.GetCurrencyUseCase
import com.example.currency_converter_app.domain.usecase.LoadCurrencyUseCase
import com.example.currency_converter_app.extention.formatNumber
import com.example.currency_converter_app.presentation.base.BaseViewModel
import com.example.currency_converter_app.presentation.navigation.Navigator
import com.example.currency_converter_app.presentation.navigation.destination.HistoryDestination
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigator: Navigator,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getCurrencyConvertResultUseCase: GetCurrencyConvertResultUseCase,
    private val loadCurrencyUseCase: LoadCurrencyUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>(), Navigator by navigator {

    private var date = 0
    private var fromCurrency = ""
    private var toCurrency = ""
    private var amount = 0.0

    override fun setInitialState(): HomeState = HomeState()

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.CloseApp -> {
                navigator.onCloseApp()
            }
            is HomeEvent.OpenHistoryScreen -> {
                navigator.navigate(route = HistoryDestination.route())
            }
            is HomeEvent.LoadAllCurrencies -> {
                loadAllCurrencies()
            }
            is HomeEvent.ConvertCurrencies -> {
                fromCurrency = event.fromCurrency
                toCurrency = event.toCurrency

                convertCurrencies()
            }
            is HomeEvent.SaveDate -> {
                date = event.date
                    .replace(("[^\\d.]").toRegex(), replacement = "")
                    .toInt()
            }
            is HomeEvent.SaveFromCurrency -> {
                fromCurrency = event.currency
            }
            is HomeEvent.SaveToCurrency -> {
                toCurrency = event.currency
            }
            is HomeEvent.SaveAmount -> {
                amount = event.amount
            }
        }
    }

    private fun loadAllCurrencies() {
        viewModelScope.launch {
            kotlin.runCatching {
                loadCurrencyUseCase()
            }.onSuccess {
                getCurrencyUseCase().collectLatest { currencies ->
                    val uahCurrency = CurrencyModel(code = "UAH", name = "UAH")
                    val currencyList = mutableListOf<CurrencyModel>()

                    currencyList.apply {
                        add(element = uahCurrency)
                        addAll(elements = currencies)
                        sortBy { it.name }
                    }

                    fromCurrency = currencyList.first().name
                    toCurrency = currencyList.first().name

                    setState {
                        copy(currencies = currencyList)
                    }
                }
            }.onFailure { throwable ->
                navigator.onError(throwable = throwable)
            }
        }
    }

    private fun convertCurrencies() {
        if (validateFields()) {
            viewModelScope.launch {
                kotlin.runCatching {
                    val currency = Currency(
                        date = date,
                        fromCurrency = fromCurrency,
                        toCurrency = toCurrency,
                        amount = amount
                    )

                    convertCurrencyUseCase(currency = currency)
                }.onSuccess {
                    getCurrencyConvertResultUseCase().collectLatest { convertResult ->
                        setState {
                            copy(convertResult = convertResult.formatNumber())
                        }
                    }
                }.onFailure { throwable ->
                    navigator.onError(throwable = throwable)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        var result = true

        if (date == 0) {
            result = false
            setEffect { HomeEffect.ShowError(message = "Please, choose date") }
        } else if (amount <= 0) {
            result = false
            setEffect { HomeEffect.ShowError(message = "Please, enter amount more than 0") }
        }

        return result
    }
}