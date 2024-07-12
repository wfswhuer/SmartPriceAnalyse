package com.quyunshuo.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 应用程序的基类Fragment，实现懒加载
 */
abstract class LazyFragment : Fragment() {
    protected var isVisibleToUser = false
        private set

    /**
     * 在这里实现Fragment数据的懒加载
     *
     * @param isVisibleToUser Fragment UI对用户是否可见
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            this.isVisibleToUser = true
            onVisible()
        } else {
            this.isVisibleToUser = false
            onInvisible()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getContentView(), container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyLoad()
    }

    protected open fun onVisible() {
        lazyLoad()
    }

    protected open fun onInvisible() {}

    protected abstract fun lazyLoad()

    protected abstract fun getContentView(): Int

    protected abstract fun initView(view: View)
}
