/**
 *  Created by jon chen
 */
package com.cxj.jetandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.cxj.jetandroid.dispatcher.TaskDispatcher
import com.cxj.jetandroid.task.InitHelperTask
import com.cxj.jetandroid.task.InitLogTask
import com.cxj.jetandroid.task.InitToastTask

class JetApplication:Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
    override fun onCreate() {
        super.onCreate()
        //Logger.addLogAdapter(AndroidLogAdapter())
        // 初始化 Toast 框架
        //Toaster.init(this)
        //Logger.addLogAdapter(AndroidLogAdapter())
        //AppHelper.init(this,BuildConfig.DEBUG)

        //1.启动器：TaskDispatcher初始化
        TaskDispatcher.init(this)
        //2.创建dispatcher实例
        val dispatcher: TaskDispatcher = TaskDispatcher.createInstance()

        //3.添加任务并且启动任务
        dispatcher.addTask(InitHelperTask(this))
            .addTask(InitToastTask())
            .addTask(InitLogTask())
            .start()

        //4.等待，需要等待的方法执行完才可以往下执行
        dispatcher.await()
    }
}