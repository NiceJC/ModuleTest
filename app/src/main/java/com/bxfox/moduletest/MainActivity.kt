package com.bxfox.moduletest

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bxfox.baselib.ktx.load

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.tv_jump).setOnClickListener {
            ARouter.getInstance().build("/a/module_a_activity").navigation()

            val image: ImageView=findViewById(R.id.iv_image)
            image.load(baseContext.assets.open(""));
        }
    }
}