/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.network
import com.cxj.jetandroid.model.Banner
import com.cxj.jetandroid.model.NetResponse
import com.cxj.jetandroid.model.SystemInfo
import com.cxj.jetandroid.model.TabInfo
import com.cxj.jetandroid.model.TabSubDataList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    /**
     * 首页轮播图
     */
    @GET("/banner/json")
    suspend  fun getHomeBanner(): NetResponse<MutableList<Banner?>?>?


    /**
     * 首页项目
     */
    @GET("/project/tree/json")
    suspend fun getTabList():NetResponse<MutableList<TabInfo?>?>?


    @GET("/project/list/{page}/json")
    suspend fun getTabSubData(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): NetResponse<TabSubDataList?>


    /**
     * 获取体系列表
     */
    @GET("/tree/json")
    suspend fun getSystemList(): NetResponse<MutableList<SystemInfo?>?>?



}