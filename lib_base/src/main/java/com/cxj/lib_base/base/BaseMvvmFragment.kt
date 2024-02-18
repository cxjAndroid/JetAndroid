/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmFragment<VB : ViewBinding, VM : ViewModel> : BaseViewBindingFragment<VB>() {
    lateinit var viewModel: VM
    open var booleanBindActivityLifecycle = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        val viewModelStore: ViewModelStoreOwner = if(booleanBindActivityLifecycle) requireActivity() else this
        viewModel = ViewModelProvider(viewModelStore)[argument[1] as Class<VM>]
    }


}