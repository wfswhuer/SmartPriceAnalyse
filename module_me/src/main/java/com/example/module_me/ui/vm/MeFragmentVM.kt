package com.example.module_me.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.module_me.bean.User
import com.example.module_me.ui.repo.MeFragmentRepo
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MeFragmentVM @Inject constructor(private val repo: MeFragmentRepo) : BaseViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            repo.getUserData().collect { userList ->
                _users.value = userList
            }
        }
    }
}
