package com.paulik.popularlibraries.domain.repo

interface CounterModelRepo {

    fun incrementOne(): Int
    fun incrementTwo(): Int
    fun incrementThree(): Int
}