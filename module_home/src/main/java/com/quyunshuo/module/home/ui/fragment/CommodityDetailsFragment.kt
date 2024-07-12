//package com.quyunshuo.module.home.ui.fragment
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.quyunshuo.module.home.R
//import com.quyunshuo.module.home.databinding.HomeCommodityDetailBinding
//
//class CommodityDetailsFragment : Fragment(), View.OnClickListener {
//
//    private var _binding: HomeCommodityDetailBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var beans: MutableList<DataBean>
//    private lateinit var commentBeans: MutableList<CommentBean>
//    private var isCollect = false
//
//    private var mTop1 = 0
//    private var mTop2 = 0
//    private var mTop3 = 0
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = HomeCommodityDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initView()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    private fun initView() {
//        // 评论 数据初始化
//        initComment()
//
//        // 设置 RecyclerView
//        val commentAdapter = CommentAdapter(requireContext())
//        commentAdapter.setBeanList(commentBeans)
//        binding.cdRcv.layoutManager = LinearLayoutManager(requireContext())
//        binding.cdRcv.setHasFixedSize(true)
//        binding.cdRcv.adapter = commentAdapter
//        binding.cdRcv.isNestedScrollingEnabled = false
//
//        // Banner 初始化
//        initListener()
//    }
//
//
//    private fun initComment() {
//        commentBeans = mutableListOf(
//            CommentBean(ContextCompat.getDrawable(requireContext(), R.drawable.ic_hot), "用户1", "整体评价：版型非常好，给老爸买的，非常喜欢，非常满意 面料品质：加了蚕丝，质感很好，面料偏哑光，显得有档次 厚薄度：初春季节正合适"),
//            CommentBean(ContextCompat.getDrawable(requireContext(), R.drawable.ic_new), "用户2", "大小合适，偏长（打死不认我腿短），偏宽（休闲裤偏直筒，微修身）总体一般吧，面料丝滑"),
//            CommentBean(ContextCompat.getDrawable(requireContext(), R.drawable.onsale), "用户3", "上身效果：跟图一样的，很好看 厚薄度：恰好，春天和夏天穿都很合适 尺码推荐：尺码标准的 整体评价：非常不错，已经第二次购买了 材质特性：很好 面料品质：质量很好，不会起球"),
//            CommentBean(ContextCompat.getDrawable(requireContext(), R.drawable.ic_vip), "用户4", "整体评价：休闲百搭 裤子不错，以前穿过a21。 面料品质：舒服")
//        )
//    }
//
//    private fun initListener() {
//        binding.cdTvCommodity.setOnClickListener(this)
//        binding.cdTvComment.setOnClickListener(this)
//        binding.cdTvDetails.setOnClickListener(this)
//        binding.cdTvParam.setOnClickListener(this)
//        binding.mibShop.setOnClickListener(this)
//        binding.mibService.setOnClickListener(this)
//        binding.mibCollect.setOnClickListener(this)
//        binding.btAddShopping.setOnClickListener(this)
//        binding.btPurchase.setOnClickListener(this)
//
//        binding.cdSv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
//            when {
//                scrollY == 0 -> binding.cdLl.visibility = View.INVISIBLE
//                scrollY < mTop2 / 2 -> {
//                    binding.cdLl.visibility = View.VISIBLE
//                    binding.cdLl.background.alpha = 50
//                }
//                scrollY < mTop2 -> {
//                    clearColor()
//                    binding.cdLl.background.alpha = 130
//                    binding.cdTvCommodity.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
//                }
//                scrollY < mTop3 / 2 -> binding.cdLl.background.alpha = 200
//                scrollY < mTop3 -> {
//                    clearColor()
//                    binding.cdLl.background.alpha = 255
//                    binding.cdTvComment.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
//                }
//                else -> {
//                    clearColor()
//                    binding.cdTvDetails.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
//                }
//            }
//        })
//    }
//
//    private fun clearColor() {
//        binding.cdTvCommodity.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_800))
//        binding.cdTvComment.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_800))
//        binding.cdTvDetails.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey_800))
//    }
//
//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.cd_tv_commodity -> binding.cdSv.smoothScrollTo(0, mTop1)
//            R.id.cd_tv_comment -> binding.cdSv.smoothScrollTo(0, mTop2)
//            R.id.cd_tv_details -> binding.cdSv.smoothScrollTo(0, mTop3)
//            R.id.mib_shop -> ToastUtil.showToast(requireContext(), "功能开发中...")
//            R.id.mib_service -> ToastUtil.showToast(requireContext(), "待开发中...")
//            R.id.mib_collect -> {
//                if (!isCollect) {
//                    ToastUtil.showToast(requireContext(), "收藏成功~")
//                    binding.mibCollect.setImageResource(R.mipmap.ic_collect_pressed)
//                    isCollect = true
//                } else {
//                    ToastUtil.showToast(requireContext(), "取消收藏~")
//                    binding.mibCollect.setImageResource(R.mipmap.ic_collect)
//                    isCollect = false
//                }
//            }
//            R.id.cd_tv_param, R.id.bt_addShopping -> {
//                val intent = Intent(requireContext(), ParameterActivity::class.java)
//                intent.putExtra("type", "addCart")
//                startActivity(intent)
//                activity?.overridePendingTransition(R.anim.bottom_in, R.anim.bottom_static)
//            }
//            R.id.bt_purchase -> {
//                val intent = Intent(requireContext(), ParameterActivity::class.java)
//                intent.putExtra("type", "purchase")
//                startActivity(intent)
//                activity?.overridePendingTransition(R.anim.bottom_in, R.anim.bottom_static)
//            }
//        }
//    }
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) {
//            mTop1 = binding.cdBanner.top
//            mTop2 = binding.tv1.top
//            mTop3 = binding.tv2.top
//        }
//    }
//}
