package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UsersRepositoryModule {

    /**
     * Унифицирование fun gitHubRepo с помощью аннотации @Binds
     */
    @Singleton
    @Binds
    abstract fun bindHubRepo(impl: UsersGitHubRepoImpl): UsersGitHubRepo

//    @Singleton
//    @Provides
//    fun gitHubRepo(
//        gitHubApi: GitHubApi,
//        projectGitHubCache: ProjectGitHubCache,
//        usersGitHubCache: UsersGitHubCache,
//        networkStatusInteractor: NetworkStatusInteractor
//    ): GitHubRepo {
//        return GitHubRepoImpl(
//            gitHubApi,
//            projectGitHubCache,
//            usersGitHubCache,
//            networkStatusInteractor
//        )
//    }

//    @Singleton
//    @Provides
//    fun counterModelRepo(): CounterModelRepo {
//        return CounterModelRepoImpl()
//    }
}