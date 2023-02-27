package com.paulik.popularlibraries.ui.counter

import com.paulik.popularlibraries.data.CounterModelRepoImpl
import com.paulik.popularlibraries.domain.CounterPresenter

class CounterBasedMvpPresenter(
    private val view: CounterPresenter
) {

    private val model = CounterModelRepoImpl()

    fun onOneCounterClicked() {
        val nextValue = model.incrementOne()
        view.setOneButtonText(nextValue.toString())
    }

    fun onTwoCounterClicked() {
        val nextValue = model.incrementTwo()
        view.setTwoButtonText(nextValue.toString())
    }

    fun onThreeCounterClicked() {
        val nextValue = model.incrementThree()
        view.setThreeButtonText(nextValue.toString())
    }
}