package com.example.module_me

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.EmptyViewModel
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.example.module_me.databinding.MeFragmentMeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 个人中心 和 更多 Fragment
 */
@AndroidEntryPoint
@Route(path = RouteUrl.Me.MeFragment)
class MeFragment : BaseFragment<MeFragmentMeBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()

    override fun MeFragmentMeBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}

    override fun createVB(): MeFragmentMeBinding {
        return MeFragmentMeBinding.inflate(layoutInflater)
    }
}