package com.paulik.popularlibraries.ui.counter

import androidx.annotation.IntRange

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(@IntRange(from = 0, to = 2) index: Int): Int {
        return counters[index]
    }

    fun increment(@IntRange(from = 0, to = 2) index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}