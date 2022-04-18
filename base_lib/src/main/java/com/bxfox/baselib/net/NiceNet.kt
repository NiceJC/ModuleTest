package com.bxfox.baselib.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy

object NiceNet {
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply{
               level=HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor(HeaderInterceptor())
            .proxy(Proxy.NO_PROXY)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <T> create(clazz :Class<T>):T{
        return retrofit.create(clazz)
    }
}