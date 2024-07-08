package com.example.module_me.ui.fragment


import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.example.module_me.databinding.MeFragmentStarBinding
import com.example.module_me.ui.vm.MeFragmentVM
import dagger.hilt.android.AndroidEntryPoint

/*
    我的收藏页面
 */
@AndroidEntryPoint
@Route(path = "/module_me/StarFragment")
class StarFragment : BaseFragment<MeFragmentStarBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()

    override fun MeFragmentStarBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}

    override fun createVB(): MeFragmentStarBinding {
        return MeFragmentStarBinding.inflate(layoutInflater)
    }
}