package com.paulik.popularlibraries.ui.root.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.paulik.popularlibraries.R

private const val DEFAULT_IMAGE_CONST = R.drawable.ic_launcher_foreground
class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .load(url)
            .placeholder(DEFAULT_IMAGE_CONST)
            .circleCrop() // Делает отображение в круглой форме
            .into(container)
    }
}