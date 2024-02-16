/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.jetandroid.model.SystemInfo
import com.cxj.jetandroid.network.ApiManager

class SystemRepository:BaseRepository() {
    suspend fun getSystemInfoList():MutableList<SystemInfo?>?{
        return sendRequest {
            ApiManager.api.getSystemList()
        }
    }
}