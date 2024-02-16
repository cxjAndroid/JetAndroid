package com.cxj.jetandroid.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import com.hjq.toast.Toaster
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 统一错误处理工具类
 */
object ExceptionHandler {

    fun handleException(e: Throwable): NetException {
        val ex: NetException
        if (e is NetException) {
            ex = NetException(e.errorCode, e.errorMsg, e)
            if (ex.errorCode == ERROR.NETWORK_ERROR.code){
                //无网络
                Toaster.show("网络异常，请尝试刷新")
            }
        } else if (e is HttpException) {
            ex = when (e.code()) {
                ERROR.UNAUTHORIZED.code -> NetException(ERROR.UNAUTHORIZED, e)
                ERROR.FORBIDDEN.code -> NetException(ERROR.FORBIDDEN, e)
                ERROR.NOT_FOUND.code -> NetException(ERROR.NOT_FOUND, e)
                ERROR.REQUEST_TIMEOUT.code -> NetException(ERROR.REQUEST_TIMEOUT, e)
                ERROR.GATEWAY_TIMEOUT.code -> NetException(ERROR.GATEWAY_TIMEOUT, e)
                ERROR.INTERNAL_SERVER_ERROR.code -> NetException(ERROR.INTERNAL_SERVER_ERROR, e)
                ERROR.BAD_GATEWAY.code -> NetException(ERROR.BAD_GATEWAY, e)
                ERROR.SERVICE_UNAVAILABLE.code -> NetException(ERROR.SERVICE_UNAVAILABLE, e)
                else -> NetException(e.code(), e.message(), e)
            }
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
                || e is MalformedJsonException
        ) {
            ex = NetException(ERROR.PARSE_ERROR, e)
        } else if (e is ConnectException) {
            ex = NetException(ERROR.NETWORK_ERROR, e)
        } else if (e is javax.net.ssl.SSLException) {
            ex = NetException(ERROR.SSL_ERROR, e)
        } else if (e is java.net.SocketException) {
            ex = NetException(ERROR.TIMEOUT_ERROR, e)
        } else if (e is java.net.SocketTimeoutException) {
            ex = NetException(ERROR.TIMEOUT_ERROR, e)
        } else if (e is java.net.UnknownHostException) {
            ex = NetException(ERROR.UN_KNOW_HOST, e)
        } else {
            ex = if (!e.message.isNullOrEmpty()) NetException(1000, e.message!!, e)
            else NetException(ERROR.UNKNOWN, e)
        }
        return ex
    }
}
