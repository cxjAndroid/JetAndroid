/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.cxj.jetandroid.repository.HomeRepository
import com.cxj.lib_common.model.Banner
import com.cxj.lib_common.model.TabInfo
import com.cxj.lib_network.base.BaseViewModel

class HomeFragmentViewModel : BaseViewModel() {
    private val homeRepository = HomeRepository()
    //val errorMsg = MutableLiveData<String>()
    var bannerLiveData = MutableLiveData<MutableList<Banner?>?>()


    fun getBanner(): LiveData<MutableList<Banner?>?> {
        return liveData {
            val response = callApi { homeRepository.getHomeBanner() }
            if (response != null) {
                bannerLiveData.value = response
                emit(response)
            }
        }
    }

    fun getTabList(): LiveData<MutableList<TabInfo?>?> {
        return liveData {
            val response = callApi { homeRepository.getTabList() }
            if (response != null) emit(response)
        }
    }

}