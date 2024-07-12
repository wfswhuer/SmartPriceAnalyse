package com.quyunshuo.androidbaseframemvvm.common.bean

import com.google.gson.annotations.SerializedName

/**
 * 公共Response
 */
data class BaseResponse<D>(
    val `data`: D,
    @SerializedName("errorCode") val code: Int,
    @SerializedName("errorMsg") val msg: String
)