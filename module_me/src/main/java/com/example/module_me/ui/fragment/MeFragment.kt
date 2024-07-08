package com.example.module_me.ui.fragment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_me.R
import com.example.module_me.databinding.MeActivityMeBinding
import com.example.module_me.ui.vm.MeFragmentVM
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = "/module_me/MeFragment")
class MeFragment() : BaseFragment<MeActivityMeBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()

    private lateinit var binding: MeActivityMeBinding

    override fun MeActivityMeBinding.initView() {
        // 初始化 binding
        binding = this

        // 登录按钮点击事件
        meBtnLogin.setOnClickListener {
            val loginFragment = ARouter.getInstance().build("/module_me/LoginFragment").navigation() as Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.nested_fragment_container, loginFragment)
                .addToBackStack("LoginFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                .commit()
            initialLayout.visibility = View.GONE
            nestedFragmentContainer.visibility = View.VISIBLE
        }

        // 切换到设置 Fragment 的按钮点击事件
        meBtnSetting.setOnClickListener {
            val newFragment = ARouter.getInstance().build("/module_me/SettingFragment").navigation() as Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.nested_fragment_container, newFragment)
                .addToBackStack("SettingFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                .commit()
            initialLayout.visibility = View.GONE
            nestedFragmentContainer.visibility = View.VISIBLE
        }
        // 切换到收藏 Fragment 的按钮点击事件
        meBtnStar.setOnClickListener {
            val newFragment = ARouter.getInstance().build("/module_me/StarFragment").navigation() as Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.nested_fragment_container, newFragment)
                .addToBackStack("StarFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                .commit()
            initialLayout.visibility = View.GONE
            nestedFragmentContainer.visibility = View.VISIBLE
        }
        // 切换到足迹 Fragment 的按钮点击事件
        meBtnStep.setOnClickListener {
            val newFragment = ARouter.getInstance().build("/module_me/StepFragment").navigation() as Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.nested_fragment_container, newFragment)
                .addToBackStack("StepFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                .commit()
            initialLayout.visibility = View.GONE
            nestedFragmentContainer.visibility = View.VISIBLE
        }

    }

    override fun initObserve() {
        // 如果需要观察 LiveData 或 ViewModel 的数据变化，可以在这里添加逻辑
    }

    override fun initRequestData() {
        // 如果需要初始化请求数据，可以在这里添加逻辑
    }

    override fun createVB(): MeActivityMeBinding {
        return MeActivityMeBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 添加对返回按钮的处理
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount > 0) {
                    childFragmentManager.popBackStack()
                    if (childFragmentManager.backStackEntryCount == 0) {
                        // 返回初始页面
                        binding.initialLayout.visibility = View.VISIBLE
                        binding.nestedFragmentContainer.visibility = View.GONE
                    }
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}
