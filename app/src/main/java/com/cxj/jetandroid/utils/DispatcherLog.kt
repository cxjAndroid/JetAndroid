package com.cxj.jetandroid.utils

import com.cxj.jetandroid.log.LogUtil


object DispatcherLog {
    var isDebug = AppHelper.isDebug()

    @JvmStatic
    fun i(msg: String?) {
        if (msg == null) {
            return
        }
        LogUtil.i(msg, tag = "StartTask")
    }
}