package com.quyunshuo.module.home.adapter

import com.android.library.YLCircleImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.bean.CommodityBean
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * 商品列表适配器
 */
class CommodityAdapter @Inject constructor() :
    BaseQuickAdapter<CommodityBean.TagGoodsBean, BaseViewHolder>(R.layout.home_item_commodity_adapter), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: CommodityBean.TagGoodsBean) {
        // 使用 BaseViewHolder 的 getView() 方法来获取视图
        val roundImageView: YLCircleImageView = holder.getView(R.id.itemImage)

        // 加载商品图片
        Picasso.get().load(item.imageSrc).fit().centerCrop().into(roundImageView)

        // 设置商品的标题
        holder.setText(R.id.itemTitle, item.name)
        // 设置商品价格
        holder.setText(R.id.itemPrice, "¥${item.price}")

        // 设置商品标签（假设标签数据固定）
        holder.setText(R.id.itemTag, "百亿补贴")
        // 设置商品评论数（假设固定值）
        holder.setText(R.id.itemComments, "666")
        // 设置商品点赞数（假设固定值）
        holder.setText(R.id.itemLikes, "666")
        // 设置商品日期（假设固定值）
        holder.setText(R.id.itemDate, "2024/07/24")
    }
}
