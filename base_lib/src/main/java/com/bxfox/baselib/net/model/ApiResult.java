package com.bxfox.baselib.net.model;

import androidx.annotation.NonNull;

public class ApiResult<T> {
    private int code = 12306;
    private String msg;
    private T result;

    public ApiResult() {
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getResult() {
        return result;
    }


    public final boolean isSuccessful() {
        return code == 200;
    }

    public final String errorMsg() {
        return null == msg ? "UnKnow Error!" : msg;
    }

    @NonNull
    @Override
    public String toString() {
        return "ApiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

}
