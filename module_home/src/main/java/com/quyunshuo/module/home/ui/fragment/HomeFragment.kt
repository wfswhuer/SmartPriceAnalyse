package com.quyunshuo.module.home.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.quyunshuo.androidbaseframemvvm.base.ktx.observeLiveData
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.adapter.CommodityAdapter
import com.quyunshuo.module.home.adapter.BannerAdapter
import com.quyunshuo.module.home.bean.BannerBean
import com.quyunshuo.module.home.databinding.HomeFragmentHomeBinding
import com.quyunshuo.module.home.ui.vm.HomeFragmentVM
import com.zhpan.bannerview.BannerViewPager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*
    首页
 */
@AndroidEntryPoint
@Route(path = RouteUrl.Home.HomeFragment)
class HomeFragment : BaseFragment<HomeFragmentHomeBinding, HomeFragmentVM>() {

    private lateinit var mBannerViewPager: BannerViewPager<BannerBean>

    private val mBannerAdapter = BannerAdapter()

    private val searchView: MaterialSearchView by lazy {
        mBinding.root.findViewById<MaterialSearchView>(R.id.search_view)
    }

    @Inject
    lateinit var commodityAdapter: CommodityAdapter

    override val mViewModel: HomeFragmentVM by viewModels()
    override fun createVB(): HomeFragmentHomeBinding {
        return HomeFragmentHomeBinding.inflate(layoutInflater)
    }

    override fun HomeFragmentHomeBinding.initView() {
        mBannerViewPager = mBinding.root.findViewById(R.id.vBanner)
        mBannerViewPager.apply {
            adapter = mBannerAdapter
            setLifecycleRegistry(lifecycle)
        }
        vArticleRv.apply {
            layoutManager = LinearLayoutManager(context)
        }
        // 文章适配器
        commodityAdapter.apply {
            setOnItemClickListener { adapter, view, position -> }

        }
        commodityAdapter.loadMoreModule.apply {
            // 开启加载更多
            isEnableLoadMore = true
            // 不自动加载
            isAutoLoadMore = false
            // 加载更多监听
            setOnLoadMoreListener { mViewModel.getArticleData() }
        }
        vArticleRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commodityAdapter
        }
        // 初始化搜索视图
        initSearchView()
    }

    override fun initObserve() {
        observeLiveData(mViewModel.bannersLD, ::processBanners)
        observeLiveData(mViewModel.articleLoadLD, ::processArticleData)
        mViewModel.isLoadAllLD.observe(this, { commodityAdapter.loadMoreModule.loadMoreEnd(false) })
    }

    override fun initRequestData() {
        mViewModel.getBanners()
        mViewModel.getArticleData()
    }

    /**
     * 处理banner数据
     */
    private fun processBanners(banners: List<BannerBean>) {
        mBannerViewPager.create()
        mBannerViewPager.refreshData(banners)
    }

    /**
     * 处理文章数据
     */
    private fun processArticleData(data: Pair<Boolean, Boolean>) {
        if (data.first) {
            // 加载更多
            commodityAdapter.loadMoreModule.apply {
                if (data.second) {
                    loadMoreComplete()
                } else {
                    loadMoreFail()
                }
            }
        } else {
            // 首次加载
            commodityAdapter.setNewInstance(mViewModel.articleList)
        }
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // 处理搜索提交
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // 处理搜索文本变化
                return false
            }
        })

        searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() {
                // 搜索视图显示时的处理
            }

            override fun onSearchViewClosed() {
                // 搜索视图关闭时的处理
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu_main, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        super.onCreateOptionsMenu(menu, inflater)
    }
}