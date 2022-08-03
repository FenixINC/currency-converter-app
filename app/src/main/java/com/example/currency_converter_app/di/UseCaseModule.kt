package com.example.currency_converter_app.di

import com.example.currency_converter_app.domain.usecase.ConvertCurrencyUseCase
import com.example.currency_converter_app.domain.usecase.ConvertCurrencyUseCaseImpl
import com.example.currency_converter_app.domain.usecase.GetCurrencyConvertResultUseCase
import com.example.currency_converter_app.domain.usecase.GetCurrencyConvertResultUseCaseImpl
import com.example.currency_converter_app.domain.usecase.GetCurrencyUseCase
import com.example.currency_converter_app.domain.usecase.GetCurrencyUseCaseImpl
import com.example.currency_converter_app.domain.usecase.HistoryUseCase
import com.example.currency_converter_app.domain.usecase.HistoryUseCaseImpl
import com.example.currency_converter_app.domain.usecase.LoadCurrencyUseCase
import com.example.currency_converter_app.domain.usecase.LoadCurrencyUseCaseImpl
import org.koin.dsl.module

fun useCaseModule() = module {
    factory<GetCurrencyUseCase> { GetCurrencyUseCaseImpl(repository = get()) }
    factory<GetCurrencyConvertResultUseCase> { GetCurrencyConvertResultUseCaseImpl(repository = get()) }
    factory<LoadCurrencyUseCase> { LoadCurrencyUseCaseImpl(repository = get()) }
    factory<ConvertCurrencyUseCase> { ConvertCurrencyUseCaseImpl(repository = get()) }
    factory<HistoryUseCase> { HistoryUseCaseImpl(repository = get()) }
}