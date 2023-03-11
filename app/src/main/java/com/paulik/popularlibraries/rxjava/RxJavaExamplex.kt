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

    fun fromIterableLong(): Observable<Long> {
        return Observable.fromIterable((1L..100L).toList())
    }

    fun fromIterableLongTwo(): Observable<Long> {
        return Observable.fromIterable((101L..200L).toList())
    }

    fun fromIterableRange(to: Int): @NonNull Observable<Int> {
        return Observable.fromIterable((1..to).toList())
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
    fun subscribeIntervalTake() {
        disposable = producer.interval()
            .take(2) // Отдает первые два элемента
//            .takeLast(2) // Отдает последнии два элемента (бесконечность, потому что
            // источник постоянно отдает элементы. Если ограничить количество элементов, то
            // бесконечности не будет)
//            .skip(1) // сколькото элементов пропусти (в данном случае будет пропущен первый элемент)
            .subscribe(
                {
                    Log.d("RxJava", "(intervalTake) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(intervalTake) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(intervalTake) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeMap() {
        disposable = producer.fromIterable()
            .map { it.toInt() * 10 }
            .map { it * 10 }
            .skip(1)
            .subscribe(
                {
                    Log.d("RxJava", "(map) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(map) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(map) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeDistinct() {
        disposable = producer.interval()
            .map { it.mod(5) }// оператор деления с остатком
            .distinct()// Возвращает только те элементы которых еще небыло
            .subscribe(
                {
                    Log.d("RxJava", "(distinct) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(distinct) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(distinct) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeFilter() {
        disposable = producer.interval()
            .filter {
                it.mod(3) == 0 // Отображаем все числа кратные 3
            }
            .subscribe(
                {
                    Log.d("RxJava", "(filter) Получен элемент $it")
                    if (it >= 12L) {
                        disposable.dispose()
                    }
                },
                {
                    Log.e("RxJava", "(filter) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(filter) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeMarge() {

        // Еще пример!!
//        val observable1 = producer.interval()
//            .filter{ it.mod(3) == 0}
//        val observable2 = producer.interval() // другой источник лучше
//            .filter{ it.mod(5) == 0}
//        Observable.merge(observable1, observable2)


        disposable = Observable.merge(producer.fromIterableLong(), producer.fromIterableLongTwo())
            .subscribe(
                {
                    Log.d("RxJava", "(merge) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(merge) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(merge) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeFlatMap() {
        val observable1 = producer.interval()
            .filter { it.mod(3) == 0 }

        // Отображаем источник в другом виде (в том в котором задаем)
        observable1.flatMap {
//            Observable.just(it * 2)
            producer.fromIterableRange(it.toInt())
        }
            .subscribe(
                {
                    Log.d("RxJava", "(FlatMap) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(FlatMap) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(FlatMap) Поток завершён")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeZip() {
        val observable1 = producer.interval()
            .filter { it.mod(3) == 0 }
//            .take(2) // взяли только первые два элемента и закончили поток
        val observable2 = producer.interval() // другой источник лучше
            .filter { it.mod(5) == 0 }
//            .filter { it < 20 } // искуственно создали условие нехватки элементов (зависаем)

        // возвращаем сумму двух (если с одного источника не хватит значений то мы зависним)
        Observable.zip(observable1, observable2, { o1, o2 ->
            "$o1 + $o2 = ${o1 + o2}"
        })
            .take(3)
            .subscribe(
                {
                    Log.d("RxJava", "(Zip) Получен элемент $it")
                },
                {
                    Log.e("RxJava", "(Zip) Получена ошибка $it", it)
                },
                {
                    Log.d("RxJava", "(Zip) Поток завершён")
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