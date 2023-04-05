package com.paulik.popularlibraries.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.ANIMATION_MODE_SLIDE
    ).show()
}