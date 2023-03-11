package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit


// производящий
class Producer {

    private fun getTimeObject(): Long {
        return System.currentTimeMillis()
    }

    fun just(): Observable<String> {
        return Observable.just("123")
    }

    fun fromIterable(): Observable<String> {
        return Observable.fromIterable(listOf("1", "2", "3", "4"))
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }

    fun timer(): Observable<Long> {
        return Observable.timer(3, TimeUnit.SECONDS)
    }

    fun range(): Observable<Int> {
        return Observable.range(50, 10)
    }

    fun fromCallable(): @NonNull Observable<Long> {
        return Observable.fromCallable {
            return@fromCallable getTimeObject()
        }
    }

    fun create(): Observable<String> {
        return Observable.create { emitter ->
            (1..10).forEach {
                emitter.onNext(it.toString())

            }
//            emitter.onError(IllegalStateException("Ошибка"))
        }
    }
}

/**
 * subscribe() - это оператор для подписки
 */

// Потребитель
class Consumer {

    // подписываемся на источник
    private val producer = Producer() // источник данных

    lateinit var disposable: Disposable

    @SuppressLint("CheckResult")
    fun subscribe() {
        producer.just()
            .subscribe(
                {
                    Log.d("RxJava", "Получен элемент $it")
                },
                {
                    Log.e("RxJava", "Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeFromIterable() {
        producer.fromIterable()
            .subscribe(
                {
                    Log.d("RxJava", "(fromIterable) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(fromIterable) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(fromIterable) Поток завершён")
                }
            ).dispose() //отключение подписчика
    }

    @SuppressLint("CheckResult")
    fun subscribeTimer() {
        disposable = producer.timer()
            // Узнали когда подписались
            .doOnSubscribe {
                Log.d("RxJava", "(timer) Начало подписки $it")

            }
            .subscribe(
                {
                    Log.d("RxJava", "(timer) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(timer) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(timer) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeRange() {
        disposable = producer.range()
            .subscribe(
                {
                    Log.d("RxJava", "(timer) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(timer) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(timer) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeFromCallable() {
        disposable = producer.fromCallable()
            .subscribe(
                {
                    Log.d("RxJava", "(fromCallable) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(fromCallable) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(fromCallable) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeCreate() {
        disposable = producer.create()
            .subscribe(
                {
                    Log.d("RxJava", "(create) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(create) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(create) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeInterval() {
        disposable = producer.interval()
            .subscribe(
                {
                    Log.d("RxJava", "(interval) Получен элемент $it")
//                    if (it) {
//                        disposable.dispose()
//                    }
                    if (it == 10L) {
                        disposable.dispose()
                    }
                },
                {
                    Log.e("RxJava", "(interval) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(interval) Поток завершён")
                }
            )


//        val observer = object : Observer<Any> {
//            // вызывается при первой подписки
//            override fun onSubscribe(d: Disposable) {
//                Log.d("RxJava", "(interval) Получен элемент $d")
//            }
//
//            // вызывается при ошибки
//            override fun onError(error: Throwable) {
//                Log.e("RxJava", "(interval) Получена ошибка $error", error)
//            }
//
//            // вызывается при
//            override fun onComplete() {
//                Log.d("RxJava", "(interval) Поток завершён")
//            }
//
//            // вызывается при
//            override fun onNext(element: Any) {
//                Log.d("RxJava", "(interval) Получен элемент $element")
//                if (element == 10L) {
//
//                }
//            }
//
//        }
//        disposable = producer.interval()
//            .subscribe(observer)
    }
}