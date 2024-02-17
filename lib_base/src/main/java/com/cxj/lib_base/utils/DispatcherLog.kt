package com.cxj.lib_base.utils

import com.cxj.lib_base.log.LogUtil


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