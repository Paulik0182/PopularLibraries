package com.paulik.popularlibraries.ui.counter

import androidx.annotation.IdRes
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.domain.CounterPresenter

class CounterBasedMvpPresenter(
    private val view: CounterPresenter
) {

    private val model = CountersModel()

    fun counterClick(@IdRes id: Int) { // todo Архитектурная ошибка
        when (id) {
            R.id.one_counter_button -> {
                val nextValue = model.increment(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.two_counter_button -> {
                val nextValue = model.increment(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.three_counter_button -> {
                val nextValue = model.increment(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }
}