package com.example.module_me.ui.fragment


import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.example.module_me.databinding.MeFragmentSettingBinding
import com.example.module_me.ui.vm.MeFragmentVM
import dagger.hilt.android.AndroidEntryPoint

/*
    设置页面
 */
@AndroidEntryPoint
@Route(path = "/module_me/SettingFragment")
class SettingFragment : BaseFragment<MeFragmentSettingBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()

    override fun MeFragmentSettingBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}

    override fun createVB(): MeFragmentSettingBinding {
        return MeFragmentSettingBinding.inflate(layoutInflater)
    }
}