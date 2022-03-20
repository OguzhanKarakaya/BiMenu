package com.example.bimenu2.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["app:loadImage"])
    fun loadImage(imageView: ImageView?, imageUrl: String?) {
        imageView?.let {
            Glide.with(it.context)
                .load(imageUrl).into(imageView)
        }
    }

}