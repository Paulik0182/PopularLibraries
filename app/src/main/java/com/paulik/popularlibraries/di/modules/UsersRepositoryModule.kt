package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.UsersScope
import com.paulik.popularlibraries.di.scope.containers.UsersScopeContainer
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UsersRepositoryModule {

    /**
     * Унифицирование fun gitHubRepo с помощью аннотации @Binds
     */
    @UsersScope
    @Binds
    abstract fun bindHubRepo(impl: UsersGitHubRepoImpl): UsersGitHubRepo

    companion object {
        @UsersScope
        @Provides
        fun usersGitHubCache(db: RoomDb): UsersGitHubCache = UsersGitHubCache(db)

        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }
}