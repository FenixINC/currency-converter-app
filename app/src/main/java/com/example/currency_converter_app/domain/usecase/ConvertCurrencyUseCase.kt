package com.example.currency_converter_app.domain.usecase

import com.example.currency_converter_app.domain.repository.CurrencyRepository
import com.example.currency_converter_app.presentation.ui.home.Currency

interface ConvertCurrencyUseCase {
    suspend operator fun invoke(currency: Currency)
}

class ConvertCurrencyUseCaseImpl(
    private val repository: CurrencyRepository
) : ConvertCurrencyUseCase {

    override suspend fun invoke(currency: Currency) {
        repository.loadCurrency(currency = currency)
    }
}