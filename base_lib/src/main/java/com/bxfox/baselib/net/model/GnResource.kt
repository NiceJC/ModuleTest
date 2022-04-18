package com.gaoneng.libs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bxfox.baselib.net.model.ApiResult
import com.bxfox.baselib.utill.NiceToast

sealed class GnResource<T : Any> {
    class Success<T : Any>(val data: T) : GnResource<T>()

    class Error<T : Any>(val error: String) : GnResource<T>()

    class Loading<T : Any> : GnResource<T>()

    companion object {

        /**
         * 接口数据转为GnResource数据
         * @param showErrorToast 接口错误时显示toast
         */
        fun <T : Any> from(
            input: LiveData<ApiResult<T>>,
            showErrorToast: Boolean = false
        ): LiveData<GnResource<T>> {
            val result = MediatorLiveData<GnResource<T>>()
            result.value = Loading()
            result.addSource(input) {
                val resource = if (it.isSuccessful) {
                    Success(it.result)
                } else {
                    if (showErrorToast) {
                        NiceToast.showToast(it.errorMsg())
                    }
                    Error(it.errorMsg())
                }
                result.value = resource
            }
            return result
        }
    }
}