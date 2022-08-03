package com.example.currency_converter_app.di

import com.example.currency_converter_app.data.database.CurrencyLocalDataSource
import com.example.currency_converter_app.data.network.source.CurrencyRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun dataSourceModule() = module {
    single { CurrencyRemoteDataSource(httpClient = get()) }
    single { CurrencyLocalDataSource(context = androidContext()) }
}