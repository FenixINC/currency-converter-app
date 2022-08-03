package com.example.currency_converter_app.domain.usecase

import com.example.currency_converter_app.data.database.HistoryModel
import com.example.currency_converter_app.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

interface HistoryUseCase : () -> Flow<List<HistoryModel>>

class HistoryUseCaseImpl(
    private val repository: CurrencyRepository
) : HistoryUseCase {

    override fun invoke(): Flow<List<HistoryModel>> {
        return repository.historyFlow
    }
}