/**
 *  Created by jon chen
 */
package com.cxj.lib_network.base

import androidx.lifecycle.ViewModel

import com.cxj.lib_network.exception.ExceptionHandler
import com.cxj.lib_network.exception.NetException
import com.hjq.toast.Toaster
import com.orhanobut.logger.Logger

open class BaseViewModel : ViewModel() {
    suspend fun <T> callApi(
        handleError: (NetException) -> Unit = { exception -> Toaster.show("errorCode:${exception.errorCode} \n errorMsg:${exception.errorMsg}") },
        request: suspend () -> T?
    ): T? {
        return try {
            request()
        } catch (e: Exception) {
            e.message?.let { Logger.e(e, it) }
            val exception = ExceptionHandler.handleException(e)
            handleError(exception)
            null
        }
    }
}