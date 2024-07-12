package com.quyunshuo.module.home.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.bean.HomePageBean
import com.quyunshuo.module.home.ui.repo.HomeFragmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentVM @Inject constructor(private val mRepo: HomeFragmentRepo) : BaseViewModel() {

    val homePageDataLD = MutableLiveData<HomePageBean>()
    val commodityDataLD = MutableLiveData<List<CommodityBean.TagGoodsBean>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getHomePageData() {
        isLoading.value = true
        viewModelScope.launch {
            mRepo.getHomePageData()
                .catch { isLoading.postValue(false) }
                .collect {
                    homePageDataLD.postValue(it)
                    isLoading.postValue(false)
                }
        }
    }

    fun getCommodityData() {
        isLoading.value = true
        viewModelScope.launch {
            mRepo.getCommodityData()
                .catch { isLoading.postValue(false) }
                .collect {
                    commodityDataLD.postValue(it)
                    isLoading.postValue(false)
                }
        }
    }
}
