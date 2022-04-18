package com.bxfox.moduletest

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bxfox.baselib.ktx.load
import com.bxfox.baseui.activity.BaseActivity

@Route(path = "/app/main")
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_jump);

//        findViewById<View>(R.id.tv_jump).setOnClickListener {
//            ARouter.getInstance().build("/a/module_a_activity").navigation()
        val builder = SpannableStringBuilder("这是开始")
        val boldSpan = StyleSpan(Typeface.BOLD);

        builder.setSpan(object :ClickableSpan(){
            override fun onClick(p0: View) {

            }

        },0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        builder.setSpan(boldSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        textView.text = builder

    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun loadData(savedInstanceState: Bundle?) {
    }

}