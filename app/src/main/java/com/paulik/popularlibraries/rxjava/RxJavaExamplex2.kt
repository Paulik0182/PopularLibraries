package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class OtherSourcesProducer {

    // рандомный метод для примеров
    private fun randomResult(): Boolean {
        Thread.sleep(Random.nextLong(1_000))
        return listOf(true, false, true, false)[Random.nextInt(2)]
    }

    // сщздание Completable (частый способ создания с помощью метода create)
    fun completable(): Completable = Completable.create { emittter ->
        val result = randomResult()
        if (result) {
            emittter.onComplete()
        } else {
            emittter.onError(java.lang.IllegalStateException("Ошибка"))
        }
    }

    // Single - идеален для обертования Http запросов
    fun single(): Single<Boolean> = Single.fromCallable() {
        val result = randomResult()
        if (!result) {
            error("Ошибка Источника")
        }
        return@fromCallable result
    }

    fun maybe() = Maybe.create<Boolean> { emitter ->
        val result = randomResult()
        if (result) {
            emitter.onSuccess(result)
        } else {
            emitter.onComplete() // успешное завершение
        }
    }

    fun timer(): Observable<Long> {
        return Observable.timer(3, TimeUnit.SECONDS)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}

class OtherConsumer {

    private val producer = OtherSourcesProducer()

    @SuppressLint("CheckResult")
    fun subscribeCompletable() {
        producer.completable()
            .subscribe(
                {
                    Log.d("rxJava", "Завершение Completable")
                },
                {
                    Log.e("rxJava", "Ошибка Completable")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeSingle() {
        producer.single()
            .subscribe(
                {
                    Log.d("rxJava", "Завершение Single $it")
                },
                {
                    Log.e("rxJava", "Ошибка Single")
                }
            )
    }

    @SuppressLint("CheckResult")
    fun subscribeMaybe() {
        producer.maybe()
            .subscribe(
                {
                    Log.d("rxJava", "Завершение maybe $it")
                },
                {
                    Log.e("rxJava", "Ошибка maybe")
                },
                {
                    Log.e("rxJava", "Завершение без результата maybe")
                }
            )
    }

    /**
     * Следующий пример с двумя подписчиками.
     * Источник один, но каждый подписчик берет данные с нуля (с самого начала)
     */
    @SuppressLint("CheckResult")
    fun subscribeTime1() {
        val time = producer.interval()
        // Первая подписка
        time
            .subscribe(
                {
                    Log.d("rxJava", "Подписчик_1. Интервал timer1 $it")
                },
                {
                    Log.e("rxJava", "Подписчик_1. Ошибка Интервал, timer1")
                },
                {
                    Log.e("rxJava", "Подписчик_1. Интервал timer1, завершён")
                }
            )
        Thread.sleep(3_000)
        // Вторая подписка
        time
            .subscribe(
                {
                    Log.d("rxJava", "Подписчик_2. Интервал timer2 $it")
                },
                {
                    Log.e("rxJava", "Подписчик_2. Ошибка Интервал, timer2")
                },
                {
                    Log.e("rxJava", "Подписчик_2. Интервал timer2, завершён")
                }
            )
    }

    /**
     * Следующий пример с двумя подписчиками.
     * Источник один. С помощью publish() сделали горячую подписку. Второй источник будет получать
     * не с нуля данные
     */
    @SuppressLint("CheckResult")
    fun subscribeTime2() {
        val time = producer.interval()
            .publish() // делает горючию подписку. Нужен метод
        // Первая подписка
        time
            .subscribe(
                {
                    Log.d("rxJava", "Подписчик_1. Интервал timer1 $it")
                },
                {
                    Log.e("rxJava", "Подписчик_1. Ошибка Интервал, timer1")
                },
                {
                    Log.e("rxJava", "Подписчик_1. Интервал timer1, завершён")
                }
            )
        time.connect()

        Thread.sleep(3_000)
        // Вторая подписка
        time
            .subscribe(
                {
                    Log.d("rxJava", "Подписчик_2. Интервал timer2 $it")
                },
                {
                    Log.e("rxJava", "Подписчик_2. Ошибка Интервал, timer2")
                },
                {
                    Log.e("rxJava", "Подписчик_2. Интервал timer2, завершён")
                }
            )
    }

    /**
     * Если необходимо подгрузить пропущенные данные. replay() Горячая подписка
     */
    @SuppressLint("CheckResult")
    fun subscribeReplay() {
        val time = producer.interval()
            .replay()
        // Первая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_1. Интервал replay $it")
            }
        time.connect()

        Thread.sleep(3_000)
        // Вторая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_2. Интервал replay $it")
            }
    }

    /**
     * Получение вторым подписчиком данных с того момента с которого он подписался.
     * refCount() Горячая подписка
     */
    @SuppressLint("CheckResult")
    fun subscribeRefCount() {
        val time = producer.interval()
            .publish()
            .refCount()
        // Первая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_1. Интервал replay $it")
            }

        Thread.sleep(3_000)
        // Вторая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_2. Интервал replay $it")
            }
    }

    /**
     * cache()() Горячая подписка
     * Работает при первой подписки, хранит все элементы и отдает все элементы каждому новому
     * подписчику
     */
    @SuppressLint("CheckResult")
    fun subscribeCache() {
        val time = producer.interval()
            .cache()
        // Первая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_1. Интервал replay $it")
            }

        Thread.sleep(3_000)
        // Вторая подписка
        time
            .subscribe {
                Log.d("rxJava", "Подписчик_2. Интервал replay $it")
            }
    }
}