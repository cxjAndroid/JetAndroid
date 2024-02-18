/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
        initViewModel()
        initView(savedInstanceState)
        initData()
    }

    protected open fun initViewModel() {

    }

    open fun initData() {

    }

    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 设置布局
     */
    open fun setContentLayout() {
        setContentView(getLayoutResId())
    }

    abstract fun getLayoutResId(): Int
    /**
     * 加载提示框
     */
    fun showLoading(msg: String?) {

    }

    /**
     * 加载提示框
     */
    fun showLoading(@StringRes res: Int) {

    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {

    }


}