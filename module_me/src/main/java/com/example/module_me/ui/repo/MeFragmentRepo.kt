package com.example.module_me.ui.repo

import com.example.module_me.net.MeApiService
import com.quyunshuo.androidbaseframemvvm.base.mvvm.m.BaseRepository
import javax.inject.Inject

class MeFragmentRepo @Inject constructor() : BaseRepository(){

    @Inject
    lateinit var mApi : MeApiService

}