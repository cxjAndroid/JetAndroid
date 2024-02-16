/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.cxj.jetandroid.model.TabSubDataList
import com.cxj.jetandroid.repository.TabFragmentRepository

class TabFragmentViewModel : BaseViewModel() {
    private val tabFragmentRepository = TabFragmentRepository()

    fun getTabSubInfo(page:Int,id:Int):LiveData<TabSubDataList?>{
        return liveData{
            val response = callApi { tabFragmentRepository.getSubTabInfo(page, id) }
            if(response!=null)emit(response)
        }
    }
}