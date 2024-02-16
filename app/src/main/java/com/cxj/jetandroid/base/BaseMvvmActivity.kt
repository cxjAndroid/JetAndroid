/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<VB: ViewBinding,VM:ViewModel> : BaseViewBindingActivity<VB>() {
    lateinit var viewModel: VM
    override fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        viewModel = ViewModelProvider(this)[argument[1] as Class<VM>]
    }
}