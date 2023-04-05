package com.paulik.popularlibraries.ui.convertor

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ConverterMvpView : MvpView {
    @AddToEndSingle
    fun getImage()

    @AddToEndSingle
    fun saveImage()
}