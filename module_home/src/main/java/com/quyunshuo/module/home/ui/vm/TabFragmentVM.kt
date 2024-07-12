package com.quyunshuo.module.home.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quyunshuo.module.home.bean.CommodityBean
import com.quyunshuo.module.home.ui.repo.HomeFragmentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabFragmentVM @Inject constructor(
    private val repository: HomeFragmentRepo
) : ViewModel() {

    private val _commodityData = MutableLiveData<List<CommodityBean.TagGoodsBean>>()
    val commodityData: LiveData<List<CommodityBean.TagGoodsBean>> = _commodityData

    fun getCommodityData(currentIndex: Int, page: Int, perPage: Int) {
        viewModelScope.launch {
            repository.getCommodityData().collect { data ->
                _commodityData.value = data
            }
        }
    }
}
