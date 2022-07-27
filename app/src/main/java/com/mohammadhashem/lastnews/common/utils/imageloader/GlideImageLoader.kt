package com.mohammadhashem.lastnews.common.utils.imageloader
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlideImageLoader @Inject constructor(@ApplicationContext val context: Context):
    ImageLoadingService {
    override fun loadImage(imageView: ImageView, imageUrl: String) {
        val imageViewReference: WeakReference<ImageView> = WeakReference(imageView)
        val imageView2 = imageViewReference.get()
        if (imageView2 != null) {
            Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false) //
                //            .placeholder(R.drawable.holder)
                .into(imageView2)
        }
    }
}
