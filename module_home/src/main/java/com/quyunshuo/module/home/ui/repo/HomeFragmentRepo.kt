package com.quyunshuo.module.home.ui.repo

import android.content.Context
import com.quyunshuo.androidbaseframemvvm.base.mvvm.m.BaseRepository
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.bean.HomePageBean
import com.quyunshuo.androidbaseframemvvm.common.data.MySQLDatabaseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeFragmentRepo @Inject constructor(
    private val context: Context // 传入上下文以获取数据库连接
) : BaseRepository() {

    private val dbHelper = MySQLDatabaseHelper(context)

    /**
     * 获取首页数据
     */
    fun getHomePageData(): Flow<HomePageBean> = flow {
        val query = "SELECT * FROM home_page_data"
        val resultSet = dbHelper.queryDatabase(query)
        val tagOptionsList = mutableListOf<HomePageBean.TagOptionsBean>()
        resultSet?.let {
            while (it.next()) {
                val tagOption = HomePageBean.TagOptionsBean(
                    platformId = it.getInt("platformId"),
                    name = it.getString("name")
                )
                tagOptionsList.add(tagOption)
            }
        }
        emit(HomePageBean(tag_options = tagOptionsList))
    }

    /**
     * 获取商品数据
     */
    fun getCommodityData(): Flow<List<CommodityBean.TagGoodsBean>> = flow {
        val query = "SELECT * FROM commodity_data"
        val resultSet = dbHelper.queryDatabase(query)
        val commodityList = mutableListOf<CommodityBean.TagGoodsBean>()
        resultSet?.let {
            while (it.next()) {
                val commodity = CommodityBean.TagGoodsBean(
                    productId = it.getInt("productId"),
                    platformId = it.getInt("platformId"),
                    name = it.getString("name"),
                    price = it.getDouble("price"),
                    categoryId = it.getInt("categoryId"),
                    productSrc = it.getString("productSrc"),
                    productContent = it.getString("productContent"),
                    storeName = it.getString("storeName"),
                    imageSrc = it.getString("imageSrc")
                )
                commodityList.add(commodity)
            }
        }
        emit(commodityList)
    }
}
