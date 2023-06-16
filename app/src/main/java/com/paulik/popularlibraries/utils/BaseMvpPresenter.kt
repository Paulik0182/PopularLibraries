package com.paulik.popularlibraries.utils

import moxy.MvpPresenter
import moxy.MvpView

abstract class BaseMvpPresenter<View : MvpView> : MvpPresenter<View>() {

    abstract fun onAttach()

    abstract fun onDetach()
}