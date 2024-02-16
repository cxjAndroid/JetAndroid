/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.jetandroid.model.TabSubDataList
import com.cxj.jetandroid.network.ApiManager

class TabFragmentRepository:BaseRepository() {

    suspend fun getSubTabInfo(page: Int, id: Int): TabSubDataList?{
        return sendRequest {
            ApiManager.api.getTabSubData(page,id)
        }
    }

}