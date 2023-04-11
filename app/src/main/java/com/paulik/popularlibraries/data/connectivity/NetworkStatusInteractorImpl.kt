package com.paulik.popularlibraries.data.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Context - следует обращатся к context как приведено ниже. В случае если фрагмент умрет и
 * Garbage Collector вызовится еще раз то все ссылки удалятся и не случится утечки памяти.
 */
class NetworkStatusInteractorImpl(
    context: Context
) : NetworkStatusInteractor {

    // Служба через которую получаем статус сети
    private val connectivityManager =
        context.getSystemService<ConnectivityManager>()

    private val networkSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    override fun getNetworkStatusSubject(): Observable<Boolean> {
        return networkSubject
    }

   override fun isOnLine() = networkSubject.value ?: false

    init {
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {

                /** доступна сеть  [network]*/
                override fun onAvailable(network: Network) {
                    networkSubject.onNext(true)
                }

                /** потеряли сеть */
                override fun onLost(network: Network) {
                    networkSubject.onNext(false)
                }

                /** не доступна сеть */
                override fun onUnavailable() {
                    networkSubject.onNext(false)
                }
            })
    }
}