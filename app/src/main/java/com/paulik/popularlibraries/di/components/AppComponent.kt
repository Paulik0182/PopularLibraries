package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.AppModule
import com.paulik.popularlibraries.di.modules.CiceroneModule
import com.paulik.popularlibraries.di.modules.DbModule
import com.paulik.popularlibraries.di.modules.NetworkModule
import com.paulik.popularlibraries.ui.users.UserRootActivity
import com.paulik.popularlibraries.ui.users.UsersRootPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DbModule::class,
        CiceroneModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun usersRepositorySubcomponent(): UsersRepositorySubcomponent

    fun usersRootPresenter(): UsersRootPresenter
    fun inject(userRootActivity: UserRootActivity)
}