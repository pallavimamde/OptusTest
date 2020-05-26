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
    /*val imageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .okHttpClient {
            OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(context))
                .build()
        }
        .build()
    Coil.setImageLoader(imageLoader)*/
    /*  val options = RequestOptions()
          .placeholder(R.mipmap.ic_launcher_round)
          .circleCrop()
          .error(R.mipmap.ic_launcher_round)

      Glide.with(this.context)
          .setDefaultRequestOptions(options)
          .load(uri)
          .into(this)*/
}