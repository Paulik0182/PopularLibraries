package com.paulik.popularlibraries.domain.interactor

import io.reactivex.rxjava3.core.Observable


interface NetworkStatusInteractor {

    fun getNetworkStatusSubject(): Observable<Boolean>
}