package com.quyunshuo.androidbaseframemvvm.app

import android.content.Context
import com.quyunshuo.module.home.ui.repo.HomeFragmentRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideHomeFragmentRepo(context: Context): HomeFragmentRepo = HomeFragmentRepo(context)
}
