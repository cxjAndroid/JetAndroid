/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

open class BaseBindingViewHolder<T:ViewBinding>(val binding: T):ViewHolder(binding.root) {

}