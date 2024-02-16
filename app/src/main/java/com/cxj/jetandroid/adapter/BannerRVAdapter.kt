/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cxj.jetandroid.base.BaseBindingViewHolder
import com.cxj.jetandroid.base.BaseBindingRVAdapter
import com.cxj.jetandroid.databinding.ImageBannerBinding
import com.cxj.jetandroid.model.Banner
import com.cxj.jetandroid.utils.setUrl

class BannerRVAdapter(data: MutableList<Banner?>) :
    BaseBindingRVAdapter<Banner?, ImageBannerBinding>(data) {

    override fun createBindingView(parent: ViewGroup): ImageBannerBinding {
        return ImageBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    }

    override fun bindData(
        data: Banner?,
        holder: BaseBindingViewHolder<ImageBannerBinding>,
        position: Int
    ) {
        holder.binding.imageBanner.setUrl(data?.imagePath)
    }
}
