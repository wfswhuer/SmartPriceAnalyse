package com.example.module_me.ui.vm

import com.example.module_me.ui.repo.MeFragmentRepo
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel


/*
    MeFragment 的VM层
 */
@HiltViewModel
class MeFragmentVM @Inject  constructor(private val repo: MeFragmentRepo) : BaseViewModel(){

}

