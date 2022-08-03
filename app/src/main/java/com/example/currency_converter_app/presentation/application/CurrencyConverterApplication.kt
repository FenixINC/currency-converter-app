package com.example.currency_converter_app.presentation.application

import android.app.Application
import com.example.currency_converter_app.di.dataSourceModule
import com.example.currency_converter_app.di.jsonModule
import com.example.currency_converter_app.di.navigationModule
import com.example.currency_converter_app.di.networkModule
import com.example.currency_converter_app.di.repositoryModule
import com.example.currency_converter_app.di.useCaseModule
import com.example.currency_converter_app.di.viewModelModule
import org.koin.android.java.KoinAndroidApplication.create
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CurrencyConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val koin = create(context = this@CurrencyConverterApplication)
            .printLogger(Level.ERROR)
            .modules(
                dataSourceModule(),
                useCaseModule(),
                jsonModule(),
                navigationModule(),
                networkModule(),
                repositoryModule(),
                viewModelModule(),
            )

        startKoin(koinApplication = koin)
    }
}