package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.CiceroneModule
import com.paulik.popularlibraries.di.modules.ContextModule
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
        ContextModule::class,
        NetworkModule::class,
//        UsersRepositoryModule::class,
//        ProjectRepositoryModule::class,
//        ForkRepositoryModule::class
    ]
)
interface AppComponent {

    fun usersRepositorySubcomponent(): UsersRepositorySubcomponent
//    fun projectRepositorySubcomponent(): ProjectRepositorySubcomponent
//    fun forkRepositorySubcomponent(): ForkRepositorySubcomponent

    //    fun usersGitHubPresenter(): UsersGitHubPresenter
//    fun forksRepoGitHubPresenterFactory(): ForksRepoGitHubPresenterFactory
//    fun projectUserGitHubPresenterFactory(): ProjectsUserGitHubPresenterFactory
    fun usersRootPresenter(): UsersRootPresenter
    fun inject(userRootActivity: UserRootActivity)
}