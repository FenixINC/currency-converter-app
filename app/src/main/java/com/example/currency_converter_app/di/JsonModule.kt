package com.example.currency_converter_app.di

import com.example.currency_converter_app.data.network.factory.JsonFactory
import org.koin.dsl.module

fun jsonModule() = module {
    single { JsonFactory.create() }
}