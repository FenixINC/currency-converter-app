package com.example.currency_converter_app.di

import com.example.currency_converter_app.data.network.factory.HttpClientFactory
import org.koin.dsl.module

fun networkModule() = module {
    single { HttpClientFactory.create(json = get()) }
}