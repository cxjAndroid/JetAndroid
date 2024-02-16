/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.jetandroid.exception.NetException
import com.cxj.jetandroid.model.NetResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {
      protected suspend fun<T> sendRequest(request:suspend ()->NetResponse<T>?):T?{
        val netResponse = withContext(Dispatchers.IO) {
            request()
        }
        netResponse ?:return null
        if(netResponse.isFailed()){
            throw  NetException(netResponse.errorCode,netResponse.errorMsg);
        }
        return netResponse.data
    }
}