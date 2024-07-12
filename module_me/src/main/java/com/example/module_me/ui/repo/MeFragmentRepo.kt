package com.example.module_me.ui.repo

import android.content.Context
import com.example.module_me.bean.User
import com.quyunshuo.androidbaseframemvvm.base.mvvm.m.BaseRepository
import com.quyunshuo.androidbaseframemvvm.common.data.MySQLDatabaseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeFragmentRepo @Inject constructor(
    private val context: Context // 传入上下文以获取数据库连接
) : BaseRepository() {

    private val dbHelper = MySQLDatabaseHelper(context)

    /**
     * 获取用户数据
     */
    fun getUserData(): Flow<List<User>> = flow {
        val userList = mutableListOf<User>()

        dbHelper.queryDatabase("SELECT user_id, username, email, tele_number FROM users")?.let { resultSet ->
            while (resultSet.next()) {
                val user = User(
                    userId = resultSet.getInt("user_id"),
                    username = resultSet.getString("username"),
                    email = resultSet.getString("email"),
                    teleNumber = resultSet.getString("tele_number")
                )
                userList.add(user)
            }
        }

        emit(userList)
    }
}
