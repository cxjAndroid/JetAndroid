/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cxj.jetandroid.adapter.TabRVAdapter
import com.cxj.lib_base.base.BaseMvvmFragment
import com.cxj.jetandroid.databinding.FragmentTabBinding
import com.cxj.lib_common.decoration.StaggeredItemDecoration
import com.cxj.jetandroid.viewmodel.TabFragmentViewModel
import com.cxj.lib_base.utils.dpToPx

class TabFragment : BaseMvvmFragment<FragmentTabBinding, TabFragmentViewModel>() {

    private lateinit var rvAdapter: TabRVAdapter
    private var hasLoadData = false

    companion object {
        const val KEY_ID = "TabFragment_id"
        fun newInstance(id: Int): TabFragment {
            val args = Bundle()
            args.putInt(KEY_ID, id)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.tabRecyclerView?.apply {
            val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            layoutManager = manager
            addItemDecoration(StaggeredItemDecoration(dpToPx(10)))
        }
    }

    override fun onResume() {
        super.onResume()
        if(!hasLoadData) {
            hasLoadData = true
            loadData()
        }
    }

    private fun loadData() {
        val id = arguments?.getInt(KEY_ID)
        viewModel.getTabSubInfo(1, id ?: 0).observe(this@TabFragment) {
            it?.let {
                binding?.tabRecyclerView?.apply {
                    rvAdapter = TabRVAdapter(requireActivity(),it.datas)
                    adapter = rvAdapter
                }
            }
        }
    }
}