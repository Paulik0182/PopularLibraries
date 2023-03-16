package com.paulik.popularlibraries

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor

class App : Application() {

    // Создаем Instance Cicerone
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

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
        NetworkStatusInteractorImpl()
    }
}

interface IContextProvider {
    val context: Context
}

object ContextProvider : IContextProvider {

    override val context: Context
        get() = App.context

}