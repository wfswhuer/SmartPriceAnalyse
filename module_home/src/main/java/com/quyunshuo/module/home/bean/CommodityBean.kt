package com.quyunshuo.module.home.bean

data class CommodityBean(
    val tagGoods: List<TagGoodsBean>
) {
    data class TagGoodsBean(
        val productId: Int,
        val platformId: Int,
        val name: String,
        val price: Double,
        val categoryId: Int,
        val productSrc: String,
        val productContent: String,
        val storeName: String,
        val imageSrc: String
    )
}
