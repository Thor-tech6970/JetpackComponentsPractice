package com.example.jetpackcomponentspractice

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.marginTop
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageFromURL")
fun ImageView.setImageFromURL(url : String){
    Glide.with(this).load(url).into(this)
}