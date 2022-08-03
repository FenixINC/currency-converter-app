package com.example.currency_converter_app.domain.usecase

import com.example.currency_converter_app.domain.repository.CurrencyRepository

interface LoadCurrencyUseCase {
    suspend operator fun invoke()
}

class LoadCurrencyUseCaseImpl(
    private val repository: CurrencyRepository
) : LoadCurrencyUseCase {

    override suspend fun invoke() {
        repository.loadAllCurrencies()
    }
}