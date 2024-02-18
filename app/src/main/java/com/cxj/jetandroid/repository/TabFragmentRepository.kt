/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.repository

import com.cxj.lib_common.model.TabSubDataList
import com.cxj.lib_network.base.BaseRepository
import com.cxj.lib_network.manager.ApiManager

class TabFragmentRepository: BaseRepository() {

    suspend fun getSubTabInfo(page: Int, id: Int): TabSubDataList?{
        return sendRequest {
            ApiManager.api.getTabSubData(page,id)
        }
    }

}