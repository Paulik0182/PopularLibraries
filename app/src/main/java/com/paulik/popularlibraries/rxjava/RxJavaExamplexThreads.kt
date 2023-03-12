package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class OtherProducerThreads {
    fun timer(): Observable<Long> {
        return Observable.timer(3, TimeUnit.SECONDS)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }

    fun just(): Observable<Long> {
        val time = Calendar.getInstance().timeInMillis
        return Observable.just(time)
    }

    fun create(): Observable<String> {

        return Observable.create { emitter ->
            Log.d("Rxjava", "Запущен на потоке: ${Thread.currentThread().name}")
            (1..5).forEach {
                emitter.onNext(it.toString())
            }
            emitter.onComplete()
        }
    }
}

class OtherConsumerThreads {

    private val producer = OtherProducerThreads()

    /**
     * Многопоточность
     */
    @SuppressLint("CheckResult")
    fun subscribe() {
        producer.interval()
            .subscribe {
                Log.d("Rxjava", "Интервал $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * Schedulers.io() - популярный планировщик io() - это пул потоков (хождение в сеть, открытие
     * файлов, ввод, вывод
     */
    @SuppressLint("CheckResult")
    fun subscribeJust() {
        producer.just()
            .subscribeOn(Schedulers.io()) // Schedulers планировщик
            .subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * computation() - Создает столько Thread сколько есть ядер в процессоре.
     * это нужно тогда когда выполнение задачи займет большую часть процессорной мощьности, но
     * это не будет много маленьких операция, это будет одна большая операция
     * Например: обработка графика, звука и всего токого, что занимает много времени
     */
    @SuppressLint("CheckResult")
    fun subscribeJustComputation() {
        producer.just()
            .subscribeOn(Schedulers.computation())
            .subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * observeOn(AndroidSchedulers.mainThread()) - Не находится в основной RxJava библиотеке.
     * Соответствует главному Thread на котором и крутится приложение
     * В Android помимо главного потока есть еще Render поток (RenderThread)
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateMainThread() {
        producer.create()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())// можно меня текущий планировщик
            .map {
                Log.d("Rxjava", "map: $it. Map Поток: ${Thread.currentThread().name}")
            }
            .observeOn(AndroidSchedulers.mainThread())// можно меня текущий планировщик
            .subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * Создание нового Thread для своих нужд
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateNewThread() {
        producer.create()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())// можно меня текущий планировщик
            .map {
                Log.d("Rxjava", "map: $it. Map Поток: ${Thread.currentThread().name}")
            }
            .observeOn(AndroidSchedulers.mainThread())// можно меня текущий планировщик
            .subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }
}