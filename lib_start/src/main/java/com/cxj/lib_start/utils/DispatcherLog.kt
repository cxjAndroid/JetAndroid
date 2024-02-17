package com.cxj.lib_start.utils

import com.cxj.lib_base.log.LogUtil
import com.cxj.lib_base.utils.AppHelper

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