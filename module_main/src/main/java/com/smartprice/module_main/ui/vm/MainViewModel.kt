package com.smartprice.module_main.ui.vm

import android.util.Log
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.smartprice.module_main.ui.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mRepository: MainRepository) : BaseViewModel() {

    /**
     * 子页面集合
     */
    val fragments: List<Fragment> = listOfNotNull(
        ARouter.getInstance()
            .build(RouteUrl.Home.HomeFragment)
            .navigation().also { result ->
                if (result == null) {
                    Log.e("MainViewModel", "HomeFragment navigation returned null")
                }
            } as? Fragment,
        ARouter.getInstance()
            .build(RouteUrl.Notice.NoticeFragment)
            .navigation().also { result ->
                if (result == null) {
                    Log.e("MainViewModel", "NoticeFragment navigation returned null")
                }
            } as? Fragment,
        ARouter.getInstance()
            .build(RouteUrl.Me.MeFragment)
            .navigation().also { result ->
                if (result == null) {
                    Log.e("MainViewModel", "MeFragment navigation returned null")
                }
            } as? Fragment
    ).filterNotNull() // 添加filterNotNull以确保列表中没有null元素
}
