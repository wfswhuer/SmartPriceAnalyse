package com.quyunshuo.module.home.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.androidbaseframemvvm.base.utils.toast
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.bean.BannerBean
import com.quyunshuo.module.home.ui.repo.HomeFragmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeFragment 的 VM 层
 */
@HiltViewModel
class HomeFragmentVM @Inject constructor(private val mRepo: HomeFragmentRepo) : BaseViewModel() {

    /**
     * 当前已获取的文章页码
     */
    private var mCurrArticlePage = 0

    /**
     * banner 数据
     */
    val bannersLD = MutableLiveData<List<BannerBean>>()

    /**
     * 文章列表数据源
     */
    val articleList = mutableListOf<CommodityBean>()

    /**
     * 文章列表数据加载 first:是否是加载更多、second:加载更多是否成功
     */
    val articleLoadLD = MutableLiveData<Pair<Boolean, Boolean>>()

    /**
     * 是否已经加载了全部
     */
    val isLoadAllLD = MutableLiveData<Boolean>()

    /**
     * 获取 banner 数据
     */
    fun getBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepo.getBanners()
                .catch { toast(it.message ?: "") }
                .collect { bannersLD.postValue(it) }
        }
    }

    /**
     * 获取文章数据
     */
    fun getArticleData() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepo.getArticleData(mCurrArticlePage)
                .catch {
                    if (mCurrArticlePage != 0) {
                        articleLoadLD.postValue(Pair(first = true, second = false))
                    }
                }
                .collect {
                    articleList.addAll(it.articleList)
                    if (mCurrArticlePage == 0) {
                        articleLoadLD.postValue(Pair(first = false, second = false))
                    } else {
                        articleLoadLD.postValue(Pair(first = true, second = true))
                    }
                    mCurrArticlePage = it.curPage
                    if (it.pageCount == it.curPage) isLoadAllLD.postValue(true)
                }
        }
    }
}