package com.example.currency_converter_app.data.network.source

import com.example.currency_converter_app.data.network.response.CurrencyModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

private const val BASE_URL = "https://bank.gov.ua/NBUStatService"
private const val DEFAULT_DATA_TYPE = "json"

class CurrencyRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun loadCurrency(
        date: Int,
        currency: String,
        dataType: String = DEFAULT_DATA_TYPE
    ): List<CurrencyModel> {
        return httpClient.get {
            url(urlString = "$BASE_URL/v1/statdirectory/exchange?valcode=$currency&date=$date&$dataType")
        }.body()
    }

    suspend fun loadAllCurrencies(): List<CurrencyModel> {
        return httpClient.get {
            url(urlString = "$BASE_URL/v1/statdirectory/exchange?$DEFAULT_DATA_TYPE")
        }.body()
    }
}