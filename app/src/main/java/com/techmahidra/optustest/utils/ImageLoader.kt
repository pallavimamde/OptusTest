package com.techmahidra.optustest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/*
* LoadImage - load image using @Glide lib
* #uri - Link to load image from path
* */
@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun ImageView.loadImage(url: String?) {
    Picasso
        .get()
        .load(url)
        .into(this)
}