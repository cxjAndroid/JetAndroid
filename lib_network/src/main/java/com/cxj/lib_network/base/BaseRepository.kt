/**
 *  Created by jon chen
 */
package com.cxj.lib_network.base


import com.cxj.lib_network.exception.NetException
import com.cxj.lib_network.response.NetResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {
      protected suspend fun<T> sendRequest(request:suspend ()-> NetResponse<T>?):T?{
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