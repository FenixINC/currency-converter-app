package com.example.currency_converter_app.di

import com.example.currency_converter_app.data.repository.CurrencyRepositoryImpl
import com.example.currency_converter_app.domain.repository.CurrencyRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    single<CurrencyRepository> {
        CurrencyRepositoryImpl(
            currencyRemoteDataSource = get(),
            currencyLocalDataSource = get()
        )
    }
}