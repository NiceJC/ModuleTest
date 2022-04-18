package com.bxfox.baselib.utill

import android.app.Application
import android.widget.Toast

object NiceToast {
private lateinit var context:Application

fun init(context:Application){
    this.context=context;
}

    fun showToast(toastString:CharSequence){
        val toast=Toast(context).apply {
            duration=Toast.LENGTH_SHORT
            setText(toastString)
        }
        toast.show()
    }
}