package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import java.util.*

class OtherProducerError {

    fun create(): Observable<String> {

        return Observable.create { emitter ->
            Log.d("Rxjava", "Запущен на потоке: ${Thread.currentThread().name}")
            (1..5).forEach {
                emitter.onNext(it.toString())
            }
            emitter.onError(IllegalStateException("Ошибка 1"))
//            emitter.onError(IllegalStateException("Ошибка 2"))
            emitter.onComplete()
        }
    }
}

class OtherConsumerError {

    private val producer = OtherProducerError()

    /**
     * onErrorReturn - он заканчивает flo (флоу) при получении ошибки.
     * Выполнение обработки заканчивается. Обрабатывается только первая ошибка.
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateErrorReturn() {
        producer.create().onErrorReturn {
                "Ошибка обработана onErrorReturn: $it"
            }.subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * onErrorResumeNext - удобно если необходимо переключить поток вслучае чего.
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateErrorResumeNext() {
        producer.create().onErrorResumeNext {
                Observable.just("Ошибка обработана onErrorReturn: $it")
            }.subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * retry() - Если мы хотим повторить операцию. Можно задать сколько раз повторить операцию
     * перезапускает Observable
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateErrorResumeNextRetry() {
        producer.create().retry(2).onErrorResumeNext {
                Observable.just("Ошибка обработана onErrorReturn: $it")
            }.subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }

    /**
     * doOnError - позволяет сделать операцию до того как будет брошена ошибка
     */
    @SuppressLint("CheckResult")
    fun subscribeCreateDoOnErrorRetry() {
        producer.create().doOnError {
                Log.e("Rxjava", "Ошибка $it")
            }.onErrorReturn {
                "Ошибка обработана onErrorReturn: $it"
            }.subscribe {
                Log.d("Rxjava", "Результат: $it. Поток: ${Thread.currentThread().name}")
            }
    }
}