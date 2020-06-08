package com.shevart.androidpracticeproject.util

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadInto(imageUrl: String) {
    Picasso.with(this.context)
        .load(Uri.parse(imageUrl))
        .into(this)
}