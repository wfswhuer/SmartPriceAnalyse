package com.smartprice.module_main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/*
    ViewPager 适配器
 */

class ViewPagerAdapter(fa: FragmentActivity, private val fragments: List<Fragment>) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}