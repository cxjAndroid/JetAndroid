/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cxj.jetandroid.databinding.ItemTabRecyclerViewBinding
import com.cxj.lib_base.webview.WebViewActivity
import com.cxj.lib_base.base.BaseBindingRVAdapter
import com.cxj.lib_base.base.BaseBindingViewHolder
import com.cxj.lib_base.utils.ViewUtils
import com.cxj.lib_base.utils.dpToPx
import com.cxj.lib_base.utils.onClick
import com.cxj.lib_common.model.TabSubInfo
import com.cxj.lib_glide.setUrl

class TabRVAdapter(val context: Context, data: MutableList<TabSubInfo?>) :
    BaseBindingRVAdapter<TabSubInfo, ItemTabRecyclerViewBinding>(data) {
    override fun createBindingView(parent: ViewGroup): ItemTabRecyclerViewBinding {
        return ItemTabRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        data: TabSubInfo?,
        holder: BaseBindingViewHolder<ItemTabRecyclerViewBinding>,
        position: Int
    ) {
        if (data == null) return
        holder.binding.apply {
            ivMainIcon.setUrl(data.envelopePic)
            tvTitle.text = data.title
            tvSubTitle.text = data.desc
            tvAuthorName.text = data.author
            tvTime.text = data.niceDate
            ViewUtils.setClipViewCornerRadius(holder.itemView, dpToPx(8))
            root.onClick {
                WebViewActivity.start(context, data.link?:"", data.title?:"")
            }
        }
    }
}