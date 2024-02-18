/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.cxj.lib_base.utils.saveAs
import com.cxj.lib_base.utils.saveAsUnChecked
import java.lang.reflect.ParameterizedType

abstract  class BaseViewBindingActivity<DB : ViewBinding>: BaseActivity() {
    lateinit var binding: DB

    override fun setContentLayout() {
        val type = javaClass.genericSuperclass
        val vbClass: Class<DB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        setContentView(binding.root)
    }

    override fun getLayoutResId(): Int {
        return 0
    }
}