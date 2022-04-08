package com.bxfox.baselib.ktx

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


fun ImageView.load(src:Any){
    Glide.with(this)
        .load(src)
        .into(this)
}

fun ImageView.loadWithCorner(src:Any,cornerRadium:Int) {
    Glide.with(this)
        .load(src)
        .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(cornerRadium,0)))
        .into(this)
}
fun ImageView.loadWithCircle(src:Any) {
    Glide.with(this)
        .load(src)
        .apply(RequestOptions.bitmapTransform(CropCircleWithBorderTransformation(0,0)))
        .into(this)
}