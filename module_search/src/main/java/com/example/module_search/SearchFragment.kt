package com.example.module_search

import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_search.databinding.SearchFragmentHomeBinding
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.EmptyViewModel
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = RouteUrl.Search.SearchFragment)
class SearchFragment: BaseFragment<SearchFragmentHomeBinding, EmptyViewModel>()  {
    override val mViewModel: EmptyViewModel by viewModels()
    override fun createVB(): SearchFragmentHomeBinding {
        return SearchFragmentHomeBinding.inflate(layoutInflater)
    }

    override fun SearchFragmentHomeBinding.initView() {}

    override fun initObserve() {}

    override fun initRequestData() {}
}