package com.quyunshuo.androidbaseframemvvm.common.helper

interface ResponseExceptionEnumCode {

    /**
     * 获取该异常枚举的code码
     * @return Int
     */
    fun getCode(): Int

    /**
     * 获取该异常枚举的描述
     * @return String
     */
    fun getMessage(): String
}