package com.quyunshuo.module.home

import android.app.Application
import android.content.Context
import com.google.auto.service.AutoService
import com.quyunshuo.androidbaseframemvvm.base.app.ApplicationLifecycle

/**
 * Home组件的伪Application
 */
@AutoService(ApplicationLifecycle::class)
class HomeApplication : ApplicationLifecycle {

    override fun onAttachBaseContext(context: Context) {}

    override fun onCreate(application: Application) {}

    override fun onTerminate(application: Application) {}

    override fun initByFrontDesk(): MutableList<() -> String> = mutableListOf()

    override fun initByBackstage() {}
}