/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.model

data class TabSubDataList(val datas: MutableList<TabSubInfo?>)

data class TabSubInfo(
    val id: Int?,
    val author: String?,
    val desc: String?,
    val envelopePic: String?,
    val link: String?,
    val niceDate: String?,
    val title: String?,
    val shareUser: String?
)