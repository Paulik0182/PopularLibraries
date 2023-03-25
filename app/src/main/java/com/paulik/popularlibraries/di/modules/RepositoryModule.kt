package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    /**
     * Унифицирование fun gitHubRepo с помощью аннотации @Binds
     */
    @Singleton
    @Binds
    abstract fun bindHubRepo(impl: GitHubRepoImpl): GitHubRepo

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