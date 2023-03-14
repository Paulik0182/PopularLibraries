package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.random.Random

/**
 * Flowable() - почти тоже самое что и Observable, но позволяет контролировать создание BackPressed.
 */

class OtherProducerFlowable {

    private fun randomResult(): Boolean {
        Thread.sleep(Random.nextLong(1_000))
        return listOf(true, false, true, false)[Random.nextInt(2)]
    }

    fun onBackPressure() = Observable.range(1, 10_000_000)

    fun backPressure() = Flowable.range(1, 1_000)
        .onBackpressureLatest()
//        .onBackpressureBuffer()

    fun single(): Single<Boolean> = Single.fromCallable() {
        val result = randomResult()
        if (!result) {
            error("Ошибка Источника")
        }
        return@fromCallable result
    }
}

class OtherConsumerFlowable {

    private val source = OtherProducerFlowable()

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun subscribe() {
        source.onBackPressure()
            .observeOn(Schedulers.io())
            .subscribe {
                Thread.sleep(1_000)
                Log.d("Rxjava", "Пришло: $it")
            }
    }

    @SuppressLint("CheckResult")
    fun subscribeBackPressure() {
        source.backPressure()
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation(), false, 1)
            .subscribe {
                Thread.sleep(1_000)
                Log.d("Rxjava", "Пришло: $it")
            }
    }

    /**
     * Чтобы остановить подписку, необходимо вызвать метод dispose, тем самым прервать подписку.
     * Но если много в Presenter подписок то будет неудобно каждый раз создавать переменную и
     * потом вызывать dispose. Существует коллекция CompositeDisposable (Композитный одноразовый)
     * которая решает указанную проблему.
     */
    @SuppressLint("CheckResult")
    fun subscribeCompositeDisposable() {
        val disposable = source.single()
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe { result: Boolean ->
            }
        compositeDisposable.add(disposable)

        val disposable2 = source.single()
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe { result: Boolean ->
            }
        compositeDisposable.add(disposable2)

        val disposable3 = source.single()
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe { result: Boolean ->
            }
        compositeDisposable.add(disposable3)

        val disposable4 = source.single()
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe { result: Boolean ->
            }
        compositeDisposable.add(disposable4)
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}