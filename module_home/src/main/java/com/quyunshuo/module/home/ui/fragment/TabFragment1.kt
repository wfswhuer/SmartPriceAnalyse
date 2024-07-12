package com.quyunshuo.module.home.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.adapter.CommodityAdapter
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.databinding.HomeFragmentTabBinding
import com.quyunshuo.module.home.ui.decoration.GridSpacingItemDecoration
import com.quyunshuo.module.home.ui.vm.TabFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TabFragment1(private val currentIndex: Int) : Fragment() {

    private var _binding: HomeFragmentTabBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var commodityAdapter: CommodityAdapter

    private val viewModel: TabFragmentVM by viewModels()

    private var PAGE = 0
    private val PER_PAGE = 15
    private val listBeans = mutableListOf<CommodityBean.TagGoodsBean>()
    private var isPrepared = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        observeLiveData()
        isPrepared = true
        lazyLoad()
    }

    private fun initView(view: View) {
        binding.foot.setCanRefresh(false)
        binding.foot.setRefreshListener(baseRefreshListener)
    }

    private fun observeLiveData() {
        viewModel.commodityData.observe(viewLifecycleOwner) { data ->
            if (PAGE == 0) {
                listBeans.clear()
            }
            listBeans.addAll(data)
            if (listBeans.isNotEmpty() && data.isEmpty()) {
                binding.foot.setCanLoadMore(false)
            }
            updateRecyclerView()
        }
    }

    private fun lazyLoad() {
        if (isPrepared && isVisible) {
            isPrepared = false
            viewModel.getCommodityData(currentIndex, PAGE, PER_PAGE)
        }
    }

    private val baseRefreshListener = object : BaseRefreshListener {
        override fun refresh() {
            Handler().postDelayed({
                PAGE = 0
                viewModel.getCommodityData(currentIndex, PAGE, PER_PAGE)
                binding.foot.finishRefresh()
            }, 1000)
        }

        override fun loadMore() {
            Handler().postDelayed({
                PAGE++
                viewModel.getCommodityData(currentIndex, PAGE, PER_PAGE)
                binding.foot.finishLoadMore()
            }, 1000)
        }
    }

    private fun updateRecyclerView() {
        val layoutManager = GridLayoutManager(activity, 2)
        binding.gridRecyclerView.layoutManager = layoutManager
        if (commodityAdapter.data.isEmpty()) {
            commodityAdapter.setNewInstance(listBeans)
            binding.gridRecyclerView.adapter = commodityAdapter
            binding.gridRecyclerView.addItemDecoration(GridSpacingItemDecoration(1, 10, false))
            val nullView = View.inflate(activity, R.layout.home_fragment_null, null)
            commodityAdapter.setEmptyView(nullView)
        } else {
            commodityAdapter.notifyDataSetChanged()
        }

        commodityAdapter.setOnItemClickListener { adapter, view, position ->
            // 点击事件处理
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        PAGE = 0
    }
}
