package com.smartprice.module_main.ui.activity

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.EmptyViewModel
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseActivity
import com.smartprice.module_main.databinding.MainActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

@AndroidEntryPoint
class SplashActivity : BaseActivity<MainActivitySplashBinding, EmptyViewModel>(), CancelAdapt {

    override val mViewModel: EmptyViewModel by viewModels()

    /**
     * 初始化View
     */
    override fun MainActivitySplashBinding.initView() {
        lifecycleScope.launch(Dispatchers.Default) {
            delay(1500)
            ARouter.getInstance()
                .build(RouteUrl.Main.MainActivity)
                .navigation()
            delay(200)
            finish()
        }
    }

    /**
     * 订阅LiveData
     */
    override fun initObserve() {}

    /**
     * 用于在页面创建时进行请求接口
     */
    override fun initRequestData() {}

    override fun setStatusBar() {}

    override fun createVB(): MainActivitySplashBinding {
        return MainActivitySplashBinding.inflate(layoutInflater)
    }
}
