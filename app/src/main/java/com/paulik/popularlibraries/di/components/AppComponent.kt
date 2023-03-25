package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.*
import com.paulik.popularlibraries.ui.users.UserRootActivity
import com.paulik.popularlibraries.ui.users.UsersRootPresenter
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubPresenterFactory
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenterFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DbModule::class,
        CiceroneModule::class,
        AppModule::class,
        NetworkModule::class,
        UsersGitHubModule::class
    ]
)
interface AppComponent {

    fun usersSubComponent(): GitHubUsersSubcomponent

    //    fun usersGitHubPresenter(): UsersGitHubPresenter
    fun forksRepoGitHubPresenterFactory(): ForksRepoGitHubPresenterFactory
    fun usersRootPresenter(): UsersRootPresenter

    fun detailsUserGitHubPresenterFactory(): DetailsUserGitHubPresenterFactory

    fun inject(userRootActivity: UserRootActivity)
}