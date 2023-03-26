package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ForksGitHubRepoImpl
import com.paulik.popularlibraries.di.scope.ForksGitHubScope
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.Binds
import dagger.Module

@Module
abstract class ForksGitHubModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
    @ForksGitHubScope
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