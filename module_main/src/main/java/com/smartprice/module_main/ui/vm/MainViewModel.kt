package com.smartprice.module_main.ui.vm

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
    val fragments: List<Fragment> = listOf(
        ARouter.getInstance()
            .build(RouteUrl.Home.HomeFragment)
            .navigation() as Fragment,
        ARouter.getInstance()
            .build(RouteUrl.Search.SearchFragment)
            .navigation() as Fragment,
        ARouter.getInstance()
            .build(RouteUrl.Me.MeFragment)
            .navigation() as Fragment
    )
}