package com.paulik.popularlibraries.ui.root.image

import android.widget.ImageView
import com.paulik.popularlibraries.R
import com.squareup.picasso.Picasso

class PicassoImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.uploading_images)
            .into(container)
        container.scaleType =
            ImageView.ScaleType.FIT_CENTER
    }
}