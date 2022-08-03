package com.example.currency_converter_app.domain.usecase

import com.example.currency_converter_app.data.network.response.CurrencyModel
import com.example.currency_converter_app.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

interface GetCurrencyUseCase : () -> Flow<List<CurrencyModel>>

class GetCurrencyUseCaseImpl(
    private val repository: CurrencyRepository
) : GetCurrencyUseCase {

    override fun invoke(): Flow<List<CurrencyModel>> {
        return repository.currencyFlow
    }
}