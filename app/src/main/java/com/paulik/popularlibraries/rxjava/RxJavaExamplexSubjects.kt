package com.paulik.popularlibraries.rxjava

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class OtherProducer {
    fun timer(): Observable<Long> {
        return Observable.timer(3, TimeUnit.SECONDS)
    }
}

class OtherSourcesProducerSubjects {

    fun publishSubject() = PublishSubject.create<String>()

}

class OtherConsumerSubjects {

    private val producer = OtherProducer()

    private val subject = OtherSourcesProducerSubjects()

    /**
     * На subject можно подписываемся темиже методами которыме подписывались для Observable.
     * Можно использовать теже методы которые использовали через источники create (onNext,
     * onComplit, onError
     *
     * subject удобно применят когда код написан в реактивном стиле и код написан не в
     * реактивном стиле, например сеть, SDK не имеет реактивный код, мы делаем подписку .....
     */
    @SuppressLint("CheckResult")
    fun subscribe() {
        val subject = subject.publishSubject()
//        subject.subscribe() // подписываем

        val time = producer.timer()
        time.subscribe {
            subject.onNext("Таймер закончился subject: $it") // публикуем в subject
        }

        // подписываемся на сообщение
        subject.subscribe {
            Log.d("RxJava", "Получили результат из subject: $it")
        }
    }

}