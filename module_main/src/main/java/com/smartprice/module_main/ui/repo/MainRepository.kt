package com.smartprice.module_main.ui.repo

import com.quyunshuo.androidbaseframemvvm.base.mvvm.m.BaseRepository
import com.smartprice.module_main.net.MainApiService
import javax.inject.Inject

class MainRepository @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var mApi: MainApiService

}