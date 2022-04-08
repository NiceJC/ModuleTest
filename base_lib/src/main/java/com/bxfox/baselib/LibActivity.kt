package com.bxfox.baselib

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bxfox.baselib.ktx.load

class LibActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    fun ImageView.load(src:Any){
        Glide.with(this)
            .load(src)
            .into(this)
    }
}