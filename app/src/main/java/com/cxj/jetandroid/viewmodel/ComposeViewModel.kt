/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cxj.jetandroid.repository.SystemRepository
import com.cxj.lib_common.model.SystemInfo
import com.cxj.lib_network.base.BaseViewModel
import kotlinx.coroutines.launch

class ComposeViewModel: BaseViewModel() {

    var systemLiveData:MutableLiveData<MutableList<SystemInfo?>?> = MutableLiveData()

    private val repository:SystemRepository by lazy {
        SystemRepository()
    }

    fun getSystemInfoList(){
        viewModelScope.launch {
            val systemInfoList = repository.getSystemInfoList()
            systemInfoList?.let {
                systemLiveData.value = it
            }
        }
    }

}
