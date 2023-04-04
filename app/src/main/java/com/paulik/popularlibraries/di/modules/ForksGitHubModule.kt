package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.ForksGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.ForksGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.ForksGitHubScope
import com.paulik.popularlibraries.di.scope.UsersGitHubScope
import com.paulik.popularlibraries.di.scope.containers.GithubUsersScopeContainer
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ForksGitHubModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
//    @Singleton
    @ForksGitHubScope
    @Binds
    abstract fun bindForks(impl: ForksGitHubRepoImpl): ForksGitHubRepo

    companion object {

        @UsersGitHubScope
        @Provides
        fun forksGitHubCache(db: RoomDb): ForksGitHubCache = ForksGitHubCache(db)

        @UsersGitHubScope
        @Provides
        fun forksScopeContainer(app: App): GithubUsersScopeContainer = app
    }
}