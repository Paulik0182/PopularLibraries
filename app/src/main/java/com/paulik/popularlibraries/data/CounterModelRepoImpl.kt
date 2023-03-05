package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.CounterEntity
import com.paulik.popularlibraries.domain.repo.CounterModelRepo

class CounterModelRepoImpl : CounterModelRepo {

    private val counters = CounterEntity(0, 0, 0)

    override fun incrementOne(): Int {
        counters.counterOne++
        return counters.counterOne
    }

    override fun incrementTwo(): Int {
        counters.counterTwo++
        return counters.counterTwo
    }

    override fun incrementThree(): Int {
        counters.counterThree++
        return counters.counterThree
    }
}