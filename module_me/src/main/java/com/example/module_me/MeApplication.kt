package com.example.module_me


import android.app.Application
import android.content.Context
import com.google.auto.service.AutoService
import com.quyunshuo.androidbaseframemvvm.base.app.ApplicationLifecycle

/**
 * Me组件的伪Application
 */
@AutoService(ApplicationLifecycle::class)
class MeApplication : ApplicationLifecycle {

    override fun onAttachBaseContext(context: Context) {}

    override fun onCreate(application: Application) {

    }

    override fun onTerminate(application: Application) {}

    override fun initByFrontDesk(): MutableList<() -> String> = mutableListOf()

    override fun initByBackstage() {}
}