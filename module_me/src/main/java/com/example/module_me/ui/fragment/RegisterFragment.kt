package com.example.module_me.ui.fragment

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_me.R
import com.example.module_me.databinding.MeActivityMeBinding
import com.example.module_me.databinding.MeFragmentRegisterBinding
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.quyunshuo.androidbaseframemvvm.common.constant.RouteUrl
import com.example.module_me.ui.vm.MeFragmentVM

import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
@Route(path = "/module_me/RegisterFragment")
class RegisterFragment : BaseFragment<MeFragmentRegisterBinding, MeFragmentVM>() {


    private lateinit var binding: MeFragmentRegisterBinding

    override val mViewModel: MeFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createVB(): MeFragmentRegisterBinding {
        return MeFragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun initObserve() {

    }

    override fun initRequestData() {

    }

    override fun MeFragmentRegisterBinding.initView() {
        binding = this
        // Register button click listener

            }
        }

