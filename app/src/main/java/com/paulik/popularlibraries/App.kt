package com.paulik.popularlibraries

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.di.components.AppComponent
import com.paulik.popularlibraries.di.components.DaggerAppComponent
import com.paulik.popularlibraries.di.modules.ContextModule
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        _instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    val networkStatusInteractor: NetworkStatusInteractor by lazy {
        NetworkStatusInteractorImpl(this)
    }
}