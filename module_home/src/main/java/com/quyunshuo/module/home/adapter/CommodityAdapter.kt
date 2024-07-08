package com.quyunshuo.module.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.bean.CommodityBean
import javax.inject.Inject

/**
 * 文章列表适配器
 */
class CommodityAdapter @Inject constructor() :
    BaseQuickAdapter<CommodityBean, BaseViewHolder>(R.layout.home_item_article), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: CommodityBean) {
        // 是否置顶文章
        if (item.top == true) {
            holder.setVisible(R.id.vTopTv, true)
        } else {
            holder.setGone(R.id.vTopTv, true)
        }
        // 设置作者
        holder.setText(
            R.id.vAuthorTv,
            if (item.author.isNullOrEmpty()) item.shareUser else item.author
        )
        // 设置标题
        holder.setText(R.id.vTitleTv, item.title)
        // 时间
        holder.setText(R.id.vDateTv, item.niceDate ?: item.niceShareDate ?: "")
        // 设置分类章节
        holder.setText(R.id.vChapterTv, "${item.superChapterName}-${item.chapterName}")
    }
}