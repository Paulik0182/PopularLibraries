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
        NetworkModule::class,
//        ProjectGitHubModule::class,
//        ForksGitHubModule::class,
//        UsersGitHubModule::class
    ]
)
interface AppComponent {

    fun usersSubComponent(): GitHubUsersSubcomponent
    fun projectSubComponent(): GitHubProjectSubcomponent
    fun forkSubComponent(): GitHubForksSubcomponent

    //    fun usersGitHubPresenter(): UsersGitHubPresenter
//    fun forksRepoGitHubPresenterFactory(): ForksRepoGitHubPresenterFactory
    fun usersRootPresenter(): UsersRootPresenter

//    fun detailsUserGitHubPresenterFactory(): DetailsUserGitHubPresenterFactory

    fun inject(userRootActivity: UserRootActivity)
}