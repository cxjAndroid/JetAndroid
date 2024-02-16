/**
 *  Created by jon chen
 */
package com.cxj.jetandroid

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cxj.jetandroid.base.BaseMvvmActivity
import com.cxj.jetandroid.databinding.ActivityMainBinding
import com.cxj.jetandroid.viewmodel.HomeFragmentViewModel


class MainActivity : BaseMvvmActivity<ActivityMainBinding, HomeFragmentViewModel>() {

    private val navView by lazy {
        binding.navView
    }


    override fun initView(savedInstanceState: Bundle?) {
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun initData() {

    }

}