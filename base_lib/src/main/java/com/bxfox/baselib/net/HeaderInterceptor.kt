package com.bxfox.baselib.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newBuilder=chain.request().newBuilder()
        val token="token"
        newBuilder.addHeader("Authorization",token)

        return chain.proceed(newBuilder.build())
    }

}