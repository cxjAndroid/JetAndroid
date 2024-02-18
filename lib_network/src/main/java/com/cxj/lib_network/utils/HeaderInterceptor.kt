package com.cxj.lib_network.utils

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")

       /* val host = request.url().host()
        val url = request.url().toString()*/

        return chain.proceed(newBuilder.build())
    }
}