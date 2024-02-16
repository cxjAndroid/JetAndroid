/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.cxj.jetandroid.utils.saveAs
import com.cxj.jetandroid.utils.saveAsUnChecked
import java.lang.reflect.ParameterizedType

abstract class BaseViewBindingFragment<VB:ViewBinding>:BaseFragment() {
    var binding: VB? = null

    override fun setContentView(inflater: LayoutInflater, container: ViewGroup?): View {
//        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        val type = javaClass.genericSuperclass
        val vbClass: Class<VB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        return binding!!.root
    }

    override fun getLayoutResId(): Int = 0

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}