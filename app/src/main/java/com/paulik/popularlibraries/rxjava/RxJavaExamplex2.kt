package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
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

}