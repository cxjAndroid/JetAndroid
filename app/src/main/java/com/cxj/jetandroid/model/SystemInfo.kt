/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class SystemInfo(
    val id: Int?,
    val courseId: Int?,
    val name: String?,       //一级名称
    val children: MutableList<SystemSecondList>? = mutableListOf(),
    val visible: Int?
)

/**
 * 二级列表
 */
@Parcelize
data class SystemSecondList(
    val id: Int?,
    val name: String?, // 二级名称
    val visible: Int?
) : Parcelable
