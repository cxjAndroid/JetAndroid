/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.lib_common.model.Banner
import com.cxj.lib_common.model.TabInfo
import com.cxj.lib_network.base.BaseRepository
import com.cxj.lib_network.manager.ApiManager

class HomeRepository: BaseRepository() {

    suspend fun getHomeBanner():MutableList<Banner?>?{
        return sendRequest {
            ApiManager.api.getHomeBanner()
        }
    }

    suspend fun getTabList():MutableList<TabInfo?>?{
        return sendRequest {
            ApiManager.api.getTabList()
        }
    }


}