package com.rubon.feeder.app_level.di.module

import com.rubon.feeder.data.microcontroller.source.ESPApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val ESP_IP = "192.168.4.1:80";

@Module
open class APIModule{

    private fun getConvertor(): Converter.Factory = GsonConverterFactory.create()

    private fun getOkHTTPClient(): OkHttpClient = OkHttpClient().newBuilder().build()

    private fun getProductsRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://$ESP_IP/")
        .client(getOkHTTPClient())
        .addConverterFactory(getConvertor())
        .build()

    @Singleton
    @Provides
    open fun getESPApi(): ESPApi {
        return getProductsRetrofit().create(ESPApi::class.java)
    }
}