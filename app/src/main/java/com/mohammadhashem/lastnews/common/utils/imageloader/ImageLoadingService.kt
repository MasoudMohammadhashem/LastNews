package com.mohammadhashem.lastnews.common.utils.imageloader

import android.widget.ImageView


interface ImageLoadingService {
    fun loadImage(imageView: ImageView, imageUrl: String?)
}
