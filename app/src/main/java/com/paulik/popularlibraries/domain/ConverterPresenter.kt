package com.paulik.popularlibraries.domain

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ConverterPresenter : MvpView {
    @AddToEndSingle
    fun getImage()

    @AddToEndSingle
    fun saveImage()
}