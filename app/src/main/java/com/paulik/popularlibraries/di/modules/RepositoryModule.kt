package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.GitHubApi
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.data.counter.CounterModelRepoImpl
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.CounterModelRepo
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    /**
     * зависимости (gitHubApi, db, networkStatusInteractor) dagger предоставляет самостоятельно
     */
    @Singleton
    @Provides
    fun gitHubRepo(
        gitHubApi: GitHubApi,
        projectGitHubCache: ProjectGitHubCache,
        usersGitHubCache: UsersGitHubCache,
        networkStatusInteractor: NetworkStatusInteractor
    ): GitHubRepo {
        return GitHubRepoImpl(
            gitHubApi,
            projectGitHubCache,
            usersGitHubCache,
            networkStatusInteractor
        )
    }

    @Singleton
    @Provides
    fun counterModelRepo(): CounterModelRepo {
        return CounterModelRepoImpl()
    }
}