/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cxj.jetandroid.adapter.BannerRVAdapter
import com.cxj.jetandroid.adapter.ViewPage2FragmentAdapter
import com.cxj.lib_base.base.BaseMvvmFragment
import com.cxj.jetandroid.databinding.FragmentMainBinding
import com.cxj.jetandroid.databinding.TabItemBinding
import com.cxj.jetandroid.viewmodel.HomeFragmentViewModel
import com.cxj.lib_common.model.TabInfo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseMvvmFragment<FragmentMainBinding, HomeFragmentViewModel>() {
    private var tabFragmentList:MutableList<Fragment> = ArrayList()
    private var tabLayoutMediator :TabLayoutMediator? = null
    private var tabs: MutableList<TabInfo> = mutableListOf()
    //override var booleanBindActivityLifecycle = false
    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun initData() {
        viewModel.getBanner().observe(this@HomeFragment) {
            it?.let {
                binding?.bannerViewPager?.apply {
                    adapter = BannerRVAdapter(it)
                }
            }
        }

        viewModel.getTabList().observe(this@HomeFragment){ it ->
            it?.forEach {
                tabFragmentList.add(TabFragment.newInstance(it?.id?:0))
                if(it!=null) tabs.add(it)
            }
            initTabAndViewPager()
        }
    }

    private fun initTabAndViewPager() {
        val viewPageAdapter =
            ViewPage2FragmentAdapter(childFragmentManager, lifecycle, tabFragmentList)
        binding?.apply {
            tabViewPager.adapter = viewPageAdapter
            tabViewPager.offscreenPageLimit = tabFragmentList.size
            tabLayoutMediator =
                TabLayoutMediator(tabLayout, tabViewPager) { tab: TabLayout.Tab, position: Int ->
                    tab.text = tabs[position].name
                    val itemBinding = TabItemBinding.inflate(layoutInflater)
                    itemBinding.tv1.text = tab.text
                    itemBinding.tv2.text = tab.text
                    tab.setCustomView(itemBinding.root)
                }
            //tabLayout和viewPager2关联起来
            tabLayoutMediator?.attach()
            //增加tab选择监听
            tabLayout.addOnTabSelectedListener(tabSelectedCall)
            //设置第一个tab效果
            val tabFirst = tabLayout.getTabAt(0)
            val customView = tabFirst?.customView as ViewGroup
            customView.getChildAt(0).visibility = View.VISIBLE
            customView.getChildAt(1).visibility = View.INVISIBLE
        }
    }

    /**
     * tab选择回调
     */
    private val tabSelectedCall = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            val customView = tab?.customView as ViewGroup
            customView.getChildAt(0).visibility = View.VISIBLE
            customView.getChildAt(1).visibility = View.INVISIBLE
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            val customView = tab?.customView as ViewGroup
            customView.getChildAt(0).visibility = View.INVISIBLE
            customView.getChildAt(1).visibility = View.VISIBLE
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator?.detach()
    }

}