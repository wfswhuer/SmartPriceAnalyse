package com.quyunshuo.module.home.ui.fragment

import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.quyunshuo.androidbaseframemvvm.base.ktx.observeLiveData
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.adapter.CommodityAdapter
import com.quyunshuo.module.home.adapter.ShouYeFragmentPagerAdapter
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.bean.HomePageBean
import com.quyunshuo.module.home.databinding.HomeFragmentHomeBinding
import com.quyunshuo.module.home.ui.vm.HomeFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@Route(path = RouteUrl.Home.HomeFragment)
class HomeFragment : BaseFragment<HomeFragmentHomeBinding, HomeFragmentVM>() {

    @Inject
    lateinit var commodityAdapter: CommodityAdapter

    private lateinit var mAdapter: ShouYeFragmentPagerAdapter

    override val mViewModel: HomeFragmentVM by viewModels()

    private var _binding: HomeFragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun createVB(): HomeFragmentHomeBinding {
        _binding = HomeFragmentHomeBinding.inflate(layoutInflater)
        return binding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun HomeFragmentHomeBinding.initView() {
        // 初始化首页资源
        mViewModel.getHomePageData()
        mViewModel.getCommodityData()

        // 设置点击事件监听器
        searchBtn.setOnClickListener(onClickListener)
        saomaBtn.setOnClickListener(onClickListener)
        messageBtn.setOnClickListener(onClickListener)
        leijiBtn.setOnClickListener(onClickListener)

        // 设置下拉刷新
        setHeadRefresh()

        // 设置RecyclerView的布局管理器和适配器
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commodityAdapter
        }
    }

    override fun initObserve() {
        // 观察首页数据的变化
        observeLiveData(mViewModel.homePageDataLD, ::processHomePageData)
        // 观察商品数据的变化
        observeLiveData(mViewModel.commodityDataLD, ::processCommodityData)
    }

    override fun initRequestData() {
        // 请求首页和商品数据
        mViewModel.getHomePageData()
        mViewModel.getCommodityData()
    }

    /**
     * 设置下拉刷新和AppBarLayout，确保在顶端时才可以下拉刷新
     */
    private fun setHeadRefresh() {
        val params = binding.appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior
        behavior.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return true
            }
        })

        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.swipeLayout.isEnabled = verticalOffset >= 0
        })

        binding.swipeLayout.setOnRefreshListener {
            Handler().postDelayed({
                mViewModel.getHomePageData()
                binding.swipeLayout.isRefreshing = false
            }, 2000)
        }
    }

    /**
     * 处理首页数据并设置视图
     */
    private fun processHomePageData(bean: HomePageBean) {
        setTabView(bean.tag_options)
    }

    /**
     * 处理商品数据
     */
    private fun processCommodityData(data: List<CommodityBean.TagGoodsBean>) {
        commodityAdapter.setNewInstance(data.toMutableList())
    }

    /**
     * 设置标签视图
     */
    private fun setTabView(tagOptions: List<HomePageBean.TagOptionsBean>) {
        val mTitles = tagOptions.map { it.name }
        val mFragments = tagOptions.map { TabFragment1(it.platformId) }

        if (binding.viewPager.adapter != null) {
            val fm = childFragmentManager
            val ft = fm.beginTransaction()

            for (i in mFragments.indices) {
                val itemId = mAdapter.getItemId(i)
                val name = makeFragmentName(binding.viewPager.id, itemId)
                val fragment = fm.findFragmentByTag(name)
                if (fragment != null) {
                    ft.remove(fragment)
                }
            }
            ft.commitNowAllowingStateLoss()
        }

        mAdapter = ShouYeFragmentPagerAdapter(childFragmentManager, tagOptions, requireContext())
        mAdapter.setItems(mFragments, mTitles)

        binding.viewPager.adapter = mAdapter

        if (binding.tabLayout.tabCount > 0) {
            binding.tabLayout.removeAllTabs()
        }
        for (i in mTitles.indices) {
            val tab = binding.tabLayout.newTab()
            tab.customView = mAdapter.getTabView(i)
            val linearLayout = binding.tabLayout.getChildAt(0) as LinearLayout
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE)
            linearLayout.dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.shuxian)
            binding.tabLayout.addTab(tab)
        }

        val view = binding.tabLayout.getTabAt(0)?.customView
        val textView = view?.findViewById<TextView>(R.id.tab_text)
        textView?.setTextColor(Color.parseColor("#FFEF2A52"))

        setLayoutWidth()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
                val view = tab.customView
                val textView = view?.findViewById<TextView>(R.id.tab_text)
                textView?.setTextColor(Color.parseColor("#FFEF2A52"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val view = tab.customView
                val textView = view?.findViewById<TextView>(R.id.tab_text)
                textView?.setTextColor(Color.parseColor("#FF000000"))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                binding.tabLayout.setScrollPosition(position, 0f, true)
                binding.tabLayout.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    /**
     * 帮助方法：生成Fragment名称
     */
    private fun makeFragmentName(viewId: Int, id: Long): String {
        return "android:switcher:$viewId:$id"
    }

    /**
     * 设置标签布局宽度
     */
    private fun setLayoutWidth() {
        binding.tabLayout.post {
            try {
                val slidingTabIndicatorField = binding.tabLayout.javaClass.getDeclaredField("slidingTabIndicator")
                slidingTabIndicatorField.isAccessible = true
                val mTabStrip = slidingTabIndicatorField.get(binding.tabLayout) as LinearLayout
                for (i in 0 until mTabStrip.childCount) {
                    val tabView = mTabStrip.getChildAt(i)
                    val textViewField = tabView.javaClass.getDeclaredField("textView")
                    textViewField.isAccessible = true
                    val mTextView = textViewField.get(tabView) as TextView
                    tabView.setPadding(0, 0, 0, 0)
                    val layoutParams = tabView.layoutParams as LinearLayout.LayoutParams
                    layoutParams.width = 0
                    layoutParams.weight = 1F
                    tabView.layoutParams = layoutParams
                    tabView.invalidate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 点击事件监听器
     */
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.search_btn -> {
                // 处理搜索按钮点击
            }
            R.id.saoma_btn -> {
                // 处理扫码按钮点击
            }
            R.id.leiji_btn -> {
                // 处理累计按钮点击
            }
            R.id.message_btn -> {
                // 处理消息按钮点击
            }
        }
    }
}
