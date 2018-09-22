package com.kanetik.blockone.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiManager {
    private const val ENDPOINT_BASE = "https://api.eosnewyork.io/v1/"

    fun <T> getService(service: Class<T>): T {
        val retrofitBuilder = Retrofit.Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl(ENDPOINT_BASE)
                .addConverterFactory(MoshiConverterFactory.create())

        return retrofitBuilder.build().create(service)
    }
}
