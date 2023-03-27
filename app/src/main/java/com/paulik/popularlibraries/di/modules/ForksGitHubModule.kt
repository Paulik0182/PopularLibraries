package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ForksGitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ForksGitHubModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
//    @ForksGitHubScope
    @Singleton
    @Binds
    abstract fun bindForks(impl: ForksGitHubRepoImpl): ForksGitHubRepo

//    companion object {
//
//        @UsersGitHubScope
//        @Provides
//        fun forksGitHubCache(db: RoomDb): ForksGitHubCache = ForksGitHubCache(db)
//
//        @UsersGitHubScope
//        @Provides
//        fun forksScopeContainer(app: App): GithubUsersScopeContainer = app
//    }
}