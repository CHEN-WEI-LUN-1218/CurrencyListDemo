package com.weilun.core.utils.imageloder

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader(private val context: Context) {
    private val glide by lazy { Glide.with(context) }

    fun loadImage(url: String, placeHolder: Drawable, imageView: ImageView) {
        glide.load(url).placeholder(placeHolder).fitCenter().circleCrop().into(imageView)
    }
}