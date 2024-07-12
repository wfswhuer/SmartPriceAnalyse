package com.quyunshuo.module_search.com.example.module_search.ui


import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_notice.databinding.NoticeFragmentHomeBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.EmptyViewModel
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment

import dagger.hilt.android.AndroidEntryPoint

/**
 * 通知
 */
@AndroidEntryPoint
@Route(path = RouteUrl.Notice.NoticeFragment)
class NoticeFragment : BaseFragment<NoticeFragmentHomeBinding, EmptyViewModel>() {

    override val mViewModel: EmptyViewModel by viewModels()
    override fun createVB(): NoticeFragmentHomeBinding {
        return NoticeFragmentHomeBinding.inflate(layoutInflater)
    }

    override fun NoticeFragmentHomeBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}
}