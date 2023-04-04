package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ProjectGitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ProjectRepositoryModule {

    /**
     * Унифицирование fun gitHubRepo с помощью аннотации @Binds
     */
    @Singleton
    @Binds
    abstract fun bindHubRepo(impl: ProjectGitHubRepoImpl): ProjectGitHubRepo

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