/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected var isViewCreate = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setContentView(inflater, container)
    }

    open fun setContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(getLayoutResId(), container,false)
    }

    /**
     * 初始化视图
     * @return Int 布局id
     */
    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreate = true
        initView(view, savedInstanceState)
        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(view: View, savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    open fun initData() {}
}