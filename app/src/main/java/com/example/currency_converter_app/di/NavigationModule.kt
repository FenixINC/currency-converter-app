package com.example.currency_converter_app.di

import com.example.currency_converter_app.presentation.navigation.Navigator
import com.example.currency_converter_app.presentation.navigation.NavigatorImpl
import org.koin.dsl.module

fun navigationModule() = module {
    single<Navigator> { NavigatorImpl() }
}