package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.GitHubScope
import com.paulik.popularlibraries.di.scope.containers.GithubUsersScopeContainer
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class GitHubRepoModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
    @GitHubScope
    @Binds
    abstract fun bindHubRepo(impl: UsersGitHubRepoImpl): UsersGitHubRepo


    companion object {

//        @GitHubScope
//        @Provides
//        fun projectGitHubCache(db: RoomDb): ProjectGitHubCache = ProjectGitHubCache(db)
//
//        @GitHubScope
//        @Provides
//        fun projectScopeContainer(app: App): GithubProjectScopeContainer = app

        @GitHubScope
        @Provides
        fun usersGitHubCache(db: RoomDb): UsersGitHubCache = UsersGitHubCache(db)

        @GitHubScope
        @Provides
        fun usersScopeContainer(app: App): GithubUsersScopeContainer = app
    }
}