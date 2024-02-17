/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.task

import android.app.Application
import com.cxj.jetandroid.BuildConfig
import com.cxj.jetandroid.utils.AppHelper
import com.cxj.lib_start.task.Task
import com.hjq.toast.Toaster
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class InitHelperTask(val application: Application) : Task() {
    //异步线程执行的Task在被调用await的时候等待
    override fun needWait(): Boolean {
        return true
    }

    override fun run() {
        AppHelper.init(application, BuildConfig.DEBUG)
    }
}

class InitToastTask : Task(){
    override fun needWait(): Boolean {
        return true
    }

    //依赖某些任务，在某些任务完成后才能执行
    override fun dependsOn(): MutableList<Class<out Task>> {
        val tasks = mutableListOf<Class<out Task?>>()
        tasks.add(InitHelperTask::class.java)
        return tasks
    }


    //执行任务初始化
    override fun run() {
        Toaster.init(AppHelper.getApplication())
    }
}

class InitLogTask:Task(){
    override fun needWait(): Boolean {
        return true
    }

    //执行任务初始化
    override fun run() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}