package com.quyunshuo.module.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.bean.HomePageBean

class ShouYeFragmentPagerAdapter : FragmentPagerAdapter {

    private var mTitles: MutableList<String> = mutableListOf() // 存储页面标题
    private var fragments: MutableList<Fragment> = mutableListOf() // 存储Fragment列表
    private var context: Context? = null // 上下文，用于布局膨胀
    private var tagOptions: List<HomePageBean.TagOptionsBean>? = null // 存储标签选项信息

    // 无参构造
    constructor(fm: FragmentManager) : super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

    // 有参构造
    constructor(fm: FragmentManager, mtagOptions: List<HomePageBean.TagOptionsBean>, mContext: Context) : super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        context = mContext
        setTagOptions(mtagOptions)
    }

    // 获取指定位置的Fragment
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    // 获取Fragment的数量
    override fun getCount(): Int {
        return fragments.size
    }

    // 获取页面标题
    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

    // 获取项目位置
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    // 动态设置Fragment和标题，并刷新适配器
    fun setItems(fragments: List<Fragment>, mTitles: List<String>) {
        this.fragments = fragments.toMutableList()
        this.mTitles = mTitles.toMutableList()
        notifyDataSetChanged()
    }

    fun setItems(fragments: List<Fragment>) {
        this.fragments = fragments.toMutableList()
        notifyDataSetChanged()
    }

    fun setTagOptions(tagOptions: List<HomePageBean.TagOptionsBean>) {
        this.tagOptions = tagOptions
        mTitles = tagOptions.map { it.name }.toMutableList()
    }

    // 获取自定义的Tab视图
    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.home_tab_item_layout, null)
        val textView = view.findViewById<TextView>(R.id.tab_text)
        val currentTagOptions = tagOptions
        if (currentTagOptions != null && position < currentTagOptions.size) {
            textView.text = currentTagOptions[position].name
        }
        return view
    }
}
