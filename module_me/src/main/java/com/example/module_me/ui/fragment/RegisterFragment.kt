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
import com.example.module_me.util.DBOpenHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
@Route(path = "/module_me/RegisterFragment")
class RegisterFragment : BaseFragment<MeFragmentRegisterBinding, MeFragmentVM>() {

    private lateinit var dbOpenHelper: DBOpenHelper

    private lateinit var binding: MeFragmentRegisterBinding

    override val mViewModel: MeFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize database helper
        dbOpenHelper = DBOpenHelper(requireContext(), "user.db", null, 1)

    }
    override fun MeFragmentRegisterBinding.initView() {
        binding = this
        // Register button click listener
        binding.btnRegister.setOnClickListener {
            val etName = binding.etRegisterUsername.text.toString()
            val etPassword = binding.etRegisterPassword.text.toString()
            val etConfirm = binding.etAgainPassword.text.toString()

            if (etName.isEmpty()) {
                Toast.makeText(requireContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show()
            } else if (!isTelPhoneNumber(etName)) {
                Toast.makeText(requireContext(), "请输入正确的手机号码！", Toast.LENGTH_SHORT).show()
            } else if (etPassword.isEmpty()) {
                Toast.makeText(requireContext(), "密码不能为空！", Toast.LENGTH_SHORT).show()
            } else if (etPassword != etConfirm) {
                Toast.makeText(requireContext(), "密码不一致！", Toast.LENGTH_SHORT).show()
            } else {
                insertData(dbOpenHelper.writableDatabase, etName, etPassword)
                Toast.makeText(requireContext(), "注册成功！", Toast.LENGTH_SHORT).show()
                val loginFragment = ARouter.getInstance().build("/module_me/LoginFragment").navigation() as Fragment
                childFragmentManager.beginTransaction()
                    .replace(R.id.nested_fragment_container, loginFragment)
                    .addToBackStack("LoginFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                    .commit()
                initialLayout.visibility = View.GONE
                nestedFragmentContainer.visibility = View.VISIBLE
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        dbOpenHelper.close()
    }

    override fun createVB(): MeFragmentRegisterBinding {
        return MeFragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun initObserve() {
    }

    override fun initRequestData() {
    }



    private fun isTelPhoneNumber(mobile: String): Boolean {
        return if (mobile.length == 11) {
            val pattern = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$")
            val matcher = pattern.matcher(mobile)
            matcher.matches()
        } else {
            false
        }
    }

    private fun insertData(readableDatabase: SQLiteDatabase, username: String, password: String) {
        val values = ContentValues()
        values.put("username", username)
        values.put("password", password)
        readableDatabase.insert("user", null, values)
    }
}
