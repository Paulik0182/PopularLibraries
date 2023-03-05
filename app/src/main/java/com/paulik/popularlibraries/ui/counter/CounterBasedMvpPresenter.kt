package com.paulik.popularlibraries.ui.counter

import com.paulik.popularlibraries.data.CounterModelRepoImpl
import com.paulik.popularlibraries.domain.CounterPresenter
import moxy.MvpPresenter

/**
 * в старой серсии Moxy дополнительно нужно было на класс поставить анатацию @InjectViewState
 * @InjectViewState – аннотация для привязывания ViewState к Presenter
 */

class CounterBasedMvpPresenter : MvpPresenter<CounterPresenter>() {

    private val model = CounterModelRepoImpl()

    fun onOneCounterClicked() {
        val nextValue = model.incrementOne()
        viewState?.setOneButtonText(nextValue.toString())
    }

    fun onTwoCounterClicked() {
        val nextValue = model.incrementTwo()
        viewState?.setTwoButtonText(nextValue.toString())
    }

    fun onThreeCounterClicked() {
        val nextValue = model.incrementThree()
        viewState?.setThreeButtonText(nextValue.toString())
    }
}