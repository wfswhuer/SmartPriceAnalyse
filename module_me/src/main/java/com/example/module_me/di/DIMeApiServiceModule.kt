package com.example.module_me.di

import com.example.module_me.net.MeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 全局作用域的Me模块网络接口代理依赖注入模块
 */
@Module
@InstallIn(SingletonComponent::class)
class DIMeApiServiceModule {

    /**
     * Me模块的[MeApiService]依赖提供方法
     */
    @Singleton
    @Provides
    fun provideHomeApiService(retrofit: Retrofit): MeApiService {
        return retrofit.create(MeApiService::class.java)
    }
}