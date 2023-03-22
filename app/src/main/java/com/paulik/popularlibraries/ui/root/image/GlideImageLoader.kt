package com.paulik.popularlibraries.ui.root.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.paulik.popularlibraries.UsedConst

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .load(url)
            .placeholder(UsedConst.imageConst.DEFAULT_IMAGE_CONST)
            .circleCrop() // Делает отображение в круглой форме
            .into(container)
    }
}