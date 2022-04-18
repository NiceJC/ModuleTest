package com.bxfox.baselib.net

import androidx.lifecycle.LiveData
import com.bxfox.baselib.net.model.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.net.SocketTimeoutException

class LiveDataCallAdapter<T>(private val responseType:Type)
    :CallAdapter<ApiResult<T>,LiveData<ApiResult<T>>>{
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<ApiResult<T>>): LiveData<ApiResult<T>> {
        return object : LiveData<ApiResult<T>>() {
            override fun onActive() {
                if (call.isExecuted) return
                call.enqueue(object : Callback<ApiResult<T>> {
                    override fun onResponse(
                        call: Call<ApiResult<T>>,
                        response: Response<ApiResult<T>>
                    ) {
                        postValue(
                            if (response.isSuccessful) {
                                response.body()
                            } else {
                                ApiResult(response.code(), response.message())
                            }
                        )
                    }

                    override fun onFailure(call: Call<ApiResult<T>>, t: Throwable) {
                        postValue(ApiResult(600, handleException(t)))
                    }
                })


            }
        }
    }

    private fun handleException(t: Throwable): String {
        return when (t) {
            is SocketTimeoutException -> {
                "网络连接超时"
            }
            else -> {
                "未知错误"
            }
        }
    }
}