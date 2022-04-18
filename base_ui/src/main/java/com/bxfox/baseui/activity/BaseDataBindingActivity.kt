package com.bxfox.baseui.activity

import androidx.databinding.ViewDataBinding

abstract class  BaseDataBindingActivity<Binding:ViewDataBinding>(
    val layoutId:Int
)
    :BaseActivity() {

}