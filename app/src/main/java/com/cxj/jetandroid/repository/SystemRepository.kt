/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.lib_common.model.SystemInfo
import com.cxj.lib_network.base.BaseRepository
import com.cxj.lib_network.manager.ApiManager

class SystemRepository: BaseRepository() {
    suspend fun getSystemInfoList():MutableList<SystemInfo?>?{
        return sendRequest {
            ApiManager.api.getSystemList()
        }
    }
}