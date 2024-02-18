/**
 *  Created by jon chen
 */
package com.cxj.lib_base.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewbinding.ViewBinding


abstract class BaseBindingRVAdapter<T, VB : ViewBinding>(var data: MutableList<T?>) :
    Adapter<BaseBindingViewHolder<ViewBinding>>() {

    private lateinit var binding: VB
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingViewHolder<ViewBinding> {
        /* val type = javaClass.genericSuperclass
        val vbClass: Class<VB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[1].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java,ViewGroup::class.java,Boolean::class.java)
        binding = method.invoke(this, LayoutInflater.from(parent.context),parent,false)!!.saveAsUnChecked()*/
        binding = createBindingView(parent)
        return BaseBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<ViewBinding>, position: Int) {
        val posData = data[position]
        holder as BaseBindingViewHolder<VB>
        bindData(posData, holder, position)
    }

    abstract fun createBindingView(parent: ViewGroup): VB

    abstract fun bindData(data: T?, holder: BaseBindingViewHolder<VB>, position: Int)

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * 设置数据
     */
    open fun notifyData(list: Collection<T>?) {
        this.data.clear()
        if (!list.isNullOrEmpty()) {
            this.data.addAll(list)
        }
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     *
     * @param newList 添加的数据
     */
    fun addAllAndNotify(newList: Collection<T>?) {
        if (newList.isNullOrEmpty()) {
            return
        }
        val lastIndex = data.size
        if (this.data.addAll(newList)) {
            notifyItemRangeInserted(lastIndex, newList.size)
        }
    }

    /**
     * 清空数据
     */
    fun clearAndNotify() {
        this.data.clear()
        notifyDataSetChanged()
    }

}