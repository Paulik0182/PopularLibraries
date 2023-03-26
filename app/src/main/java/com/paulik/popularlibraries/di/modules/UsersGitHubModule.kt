package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.UsersGitHubScope
import com.paulik.popularlibraries.di.scope.containers.GithubUsersScopeContainer
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UsersGitHubModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
    @UsersGitHubScope
    @Binds
    abstract fun bindHubRepo(impl: UsersGitHubRepoImpl): UsersGitHubRepo

    companion object {

        @UsersGitHubScope
        @Provides
        fun usersGitHubCache(db: RoomDb): UsersGitHubCache = UsersGitHubCache(db)

        @UsersGitHubScope
        @Provides
        fun usersScopeContainer(app: App): GithubUsersScopeContainer = app
    }
}