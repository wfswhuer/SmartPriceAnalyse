package com.quyunshuo.androidbaseframemvvm.common.constant

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/28
 * @Class: RoutePath
 * @Remark: 路由地址
 */
object RouteUrl {
    /**
     * Main模块
     */
    object Main {
        /**
         * 首页
         */
        const val MainActivity = "/module_main/MainActivity"
    }

    /**
     * 首页模块
     */
    object Home {

        /**
         * 首页Fragment
         */
        const val HomeFragment = "/module_home/HomeFragment"
    }

    /**
     * 订阅号模块
     */
    object Search {

        /**
         * 订阅号Fragment
         */
        const val SearchFragment = "/module_search/SearchFragment"
    }

    /**
     * 个人中心和更多模块
     */
    object Me {

        /**
         * 个人中心和更多Fragment
         */
        const val MeFragment = "/module_me/MeFragment"
    }
}