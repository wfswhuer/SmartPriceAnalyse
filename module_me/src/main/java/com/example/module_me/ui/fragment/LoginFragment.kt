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
import com.example.module_me.util.DBOpenHelper
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
@Route(path = "/module_me/LoginFragment")
class LoginFragment : BaseFragment<MeFragmentLoginBinding, MeFragmentVM>() {

    override val mViewModel: MeFragmentVM by viewModels()
    private lateinit var dbOpenHelper: DBOpenHelper // 声明数据库操作对象
    private lateinit var dbpassword: String // 数据库中的密码
    private lateinit var binding: MeFragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化数据库操作对象
        dbOpenHelper = DBOpenHelper(requireContext(), "user.db", null, 1)

    }

    override fun MeFragmentLoginBinding.initView() {
        binding = this
        // 登录按钮点击事件
        btnLogin.setOnClickListener {
            val etpassword = etPassword.text.toString()
            val key = etUsername.text.toString()

            // 正则验证手机号码格式
            if (!isTelPhoneNumber(key)) {
                Toast.makeText(requireContext(), "请输入正确的手机号！", Toast.LENGTH_SHORT).show()
            } else {
                // 查询数据库
                val cursor = dbOpenHelper.readableDatabase.query(
                    "user",
                    null,
                    "username = ?",
                    arrayOf(key),
                    null,
                    null,
                    null
                )

                val resultList = ArrayList<Map<String, String>>()
                while (cursor.moveToNext()) {
                    val map = HashMap<String, String>()
                    map["username"] = cursor.getString(1) // 获取用户名
                    map["password"] = cursor.getString(2) // 获取密码
                    resultList.add(map)
                    dbpassword = map["password"]!! // 获取数据库中的密码
                }

                if (resultList.isEmpty()) {
                    // 如果数据库中没有查询到相关用户数据
                    Toast.makeText(requireContext(), "该用户名未注册，请先注册", Toast.LENGTH_LONG).show()
                } else {
                    // 显示查询结果
                    val simpleAdapter = SimpleAdapter(
                        requireContext(),
                        resultList,
                        R.layout.userdata_main,
                        arrayOf("username", "password"),
                        intArrayOf(R.id.result_name, R.id.result_grade)
                    )
                    binding.testText.adapter = simpleAdapter

                    // 检查密码是否匹配
                    if (etpassword == dbpassword) {
                        Toast.makeText(requireContext(), "登录成功！", Toast.LENGTH_SHORT).show()
                        // 跳转到MainActivity
                        val loginFragment = ARouter.getInstance().build("/module_me/RegisterFragment").navigation() as Fragment
                        childFragmentManager.beginTransaction()
                            .replace(R.id.nested_fragment_container, loginFragment)
                            .addToBackStack("RegisterFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                            .commit()
                        initialLayout.visibility = View.GONE
                        nestedFragmentContainer.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(requireContext(), "密码错误！", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // 注册按钮点击事件
        btnRegister1.setOnClickListener {
            val loginFragment = ARouter.getInstance().build("/module_me/RegisterFragment").navigation() as Fragment
            childFragmentManager.beginTransaction()
                .replace(R.id.nested_fragment_container, loginFragment)
                .addToBackStack("RegisterFragment") // 添加到返回栈，用户可以返回到上一个 Fragment
                .commit()
            initialLayout.visibility = View.GONE
            nestedFragmentContainer.visibility = View.VISIBLE
        }
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
        if (::dbOpenHelper.isInitialized) {
            dbOpenHelper.close()
        }
    }
}
