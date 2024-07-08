package com.example.module_me.ui.fragment


import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.example.module_me.databinding.MeFragmentStepBinding
import com.example.module_me.ui.vm.MeFragmentVM
import dagger.hilt.android.AndroidEntryPoint

/*
    我的足迹页面
 */
@AndroidEntryPoint
@Route(path = "/module_me/StepFragment")
class StepFragment : BaseFragment<MeFragmentStepBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()

    override fun MeFragmentStepBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}

    override fun createVB(): MeFragmentStepBinding {
        return MeFragmentStepBinding.inflate(layoutInflater)
    }
}