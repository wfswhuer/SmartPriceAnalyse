package com.example.module_me.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_me.R
import com.example.module_me.databinding.MeFragmentLoginBinding
import com.example.module_me.ui.vm.MeFragmentVM

import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
@Route(path = "/module_me/LoginFragment")
class LoginFragment : BaseFragment<MeFragmentLoginBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()


    private lateinit var binding: MeFragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun MeFragmentLoginBinding.initView() {
        binding = this
        // 登录按钮点击事件

    }

    override fun initObserve() {
        // 观察LiveData
    }

    override fun initRequestData() {
        // 请求数据
    }

    override fun createVB(): MeFragmentLoginBinding {
        return MeFragmentLoginBinding.inflate(layoutInflater)
    }

    // 正则验证手机号码
    private fun isTelPhoneNumber(mobile: String): Boolean {
        return mobile.length == 11 && Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$").matcher(mobile).matches()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
