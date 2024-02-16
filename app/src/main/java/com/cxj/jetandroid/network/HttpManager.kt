/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.network

import android.util.Log
import com.cxj.jetandroid.exception.ERROR
import com.cxj.jetandroid.exception.NetException
import com.cxj.jetandroid.utils.AppHelper
import com.cxj.jetandroid.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpManager {
    private const val BASE_URL = HttpConstant.BASE_URL
    private val retrofit: Retrofit

    init {
        retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> create(api: Class<T>): T {
        return retrofit.create(api)
    }

    private fun initOkHttpClient(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
        // 添加参数拦截器
        val interceptors = mutableListOf<Interceptor>()
        build.addInterceptor(HeaderInterceptor())

        //日志拦截器
        val logInterceptor = HttpLoggingInterceptor { message: String ->
            Log.i("okhttp", "data:$message")
        }

        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        build.addInterceptor(logInterceptor)
        //网络状态拦截
        build.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                if (NetworkUtil.isConnected(AppHelper.getApplication())) {
                    val request = chain.request()
                    return chain.proceed(request)
                } else {
                    throw NetException(ERROR.NETWORK_ERROR)
                }
            }
        })
        return build.build()
    }

}

object ApiManager {
    val api by lazy {
        HttpManager.create(ApiInterface::class.java)
    }
}