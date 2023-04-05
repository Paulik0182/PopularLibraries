package com.paulik.popularlibraries

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.di.components.*
import com.paulik.popularlibraries.di.modules.AppModule
import com.paulik.popularlibraries.di.scope.containers.ForkScopeContainer
import com.paulik.popularlibraries.di.scope.containers.ProjectScopeContainer
import com.paulik.popularlibraries.di.scope.containers.UsersScopeContainer
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor

class App : Application(), UsersScopeContainer, ProjectScopeContainer, ForkScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    var usersRepositorySubcomponent: UsersRepositorySubcomponent? = null
    var projectRepositorySubcomponent: ProjectRepositorySubcomponent? = null
    var forkRepositorySubcomponent: ForkRepositorySubcomponent? = null

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        _instance = this
    }

    override fun initUsersRepositorySubcomponent() =
        appComponent.usersRepositorySubcomponent().also {
            usersRepositorySubcomponent = it
        }

    override fun initProjectRepositorySubcomponent() =
        appComponent.usersRepositorySubcomponent().projectRepositorySubcomponent().also {
            projectRepositorySubcomponent = it
        }

    override fun initForkRepositorySubcomponent() =
        appComponent.usersRepositorySubcomponent().projectRepositorySubcomponent()
            .forkRepositorySubcomponent().also {
                forkRepositorySubcomponent = it
            }

    override fun destroyUsersRepositorySubcomponent() {
        usersRepositorySubcomponent = null
    }

    override fun destroyProjectRepositorySubcomponent() {
        projectRepositorySubcomponent = null
    }

    override fun destroyForkRepositorySubcomponent() {
        forkRepositorySubcomponent = null
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