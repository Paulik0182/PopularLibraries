package com.paulik.popularlibraries

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.di.components.AppComponent
import com.paulik.popularlibraries.di.components.DaggerAppComponent
import com.paulik.popularlibraries.di.components.GitHubUsersSubcomponent
import com.paulik.popularlibraries.di.modules.AppModule
import com.paulik.popularlibraries.di.scope.containers.GithubUsersScopeContainer
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor

class App : Application(), GithubUsersScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    var usersSubcomponent: GitHubUsersSubcomponent? = null

    //    var projectSubcomponent: GitHubProjectSubcomponent? = null
    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        _instance = this
    }

    override fun initUsersSubcomponent() = appComponent.usersSubComponent().also {
        usersSubcomponent = it
    }

    override fun destroyUsersSubcomponent() {
        usersSubcomponent = null
    }

//    override fun initProjectSubcomponent() = appComponent.usersSubComponent().projectSubcomponent().also {
//        projectSubcomponent = it
//    }
//
//    override fun destroyProjectSubcomponent(){
//        projectSubcomponent = null
//    }

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

interface IContextProvider {
    val context: Context
}

object ContextProvider : IContextProvider {

    override val context: Context
        get() = App.context

}