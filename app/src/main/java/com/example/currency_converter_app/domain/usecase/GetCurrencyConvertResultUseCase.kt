package com.example.currency_converter_app.domain.usecase

import com.example.currency_converter_app.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

interface GetCurrencyConvertResultUseCase : () -> Flow<Double>

class GetCurrencyConvertResultUseCaseImpl(
    private val repository: CurrencyRepository
) : GetCurrencyConvertResultUseCase {

    override fun invoke(): Flow<Double> {
        return repository.convertResultFlow
    }
}