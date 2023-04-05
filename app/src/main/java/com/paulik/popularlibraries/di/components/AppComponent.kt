package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.*
import com.paulik.popularlibraries.ui.users.UserRootActivity
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import com.paulik.popularlibraries.ui.users.UsersRootPresenter
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenterFactory
import com.paulik.popularlibraries.ui.users.projects.ProjectsUserGitHubPresenterFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        UsersRepositoryModule::class,
        ProjectRepositoryModule::class,
        ForkRepositoryModule::class
    ]
)
interface AppComponent {

    fun usersGitHubPresenter(): UsersGitHubPresenter
    fun forksRepoGitHubPresenterFactory(): ForksRepoGitHubPresenterFactory
    fun projectUserGitHubPresenterFactory(): ProjectsUserGitHubPresenterFactory
    fun usersRootPresenter(): UsersRootPresenter
    fun inject(userRootActivity: UserRootActivity)
}