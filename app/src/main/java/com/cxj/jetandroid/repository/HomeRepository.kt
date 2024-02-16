/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.jetandroid.model.Banner
import com.cxj.jetandroid.model.TabInfo
import com.cxj.jetandroid.model.TabSubDataList
import com.cxj.jetandroid.network.ApiManager

class HomeRepository:BaseRepository() {

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