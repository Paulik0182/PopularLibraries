package com.paulik.popularlibraries.ui.root.image

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}