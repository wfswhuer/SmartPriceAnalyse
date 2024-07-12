package com.quyunshuo.module.home.di

import com.quyunshuo.module.home.R
import com.quyunshuo.module.home.bean.HomePageBean
import com.quyunshuo.module.home.net.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 全局作用域的Home模块网络接口代理依赖注入模块
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class DIHomeApiServiceModule {

    /**
     * Home模块的[HomeApiService]依赖提供方法
     *
     * @param retrofit Retrofit
     * @return HomeApiService
     */
    @Singleton
    @Provides
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }

}
