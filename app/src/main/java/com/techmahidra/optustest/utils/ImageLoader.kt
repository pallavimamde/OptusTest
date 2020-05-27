package com.techmahidra.optustest.utils

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import java.lang.Exception

/*
* LoadImage - load image using @picasso lib
* #url - Link to load image from path
* */
@BindingAdapter(value = ["imageUrl"], requireAll = false)
fun ImageView.loadImage(url: String?) {
    Picasso
        .get()
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(this, object: Callback {
            override fun onSuccess() {
                Log.d("TAG", "onSuccess")
            }

            override fun onError(e: Exception?) {
                Toast.makeText(UserInfoApplication.applicationContext(), "An error occurred", Toast.LENGTH_SHORT).show()
            }

        })
}