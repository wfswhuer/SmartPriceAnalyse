package com.quyunshuo.module.home.net

import com.quyunshuo.androidbaseframemvvm.common.bean.BaseResponse
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.bean.HomePageBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 首页模块的接口代理类
 *
 * @author Qu Yunshuo
 * @since 2021/8/5 10:55 下午
 */
interface HomeApiService {

    @GET("home/pageData")
    suspend fun getHomePageData(): BaseResponse<HomePageBean>

    @GET("home/commodityData")
    suspend fun getCommodityData(): BaseResponse<List<CommodityBean.TagGoodsBean>>
}
