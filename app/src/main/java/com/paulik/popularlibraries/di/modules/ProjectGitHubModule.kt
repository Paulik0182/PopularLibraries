package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.ProjectGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.ProjectGitHubScope
import com.paulik.popularlibraries.di.scope.containers.GithubProjectScopeContainer
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ProjectGitHubModule {

    /**
     * Унифицирование код с помощью аннотации @Binds
     */
    //    @Singleton
    @ProjectGitHubScope
    @Binds
    abstract fun bindProject(impl: ProjectGitHubRepoImpl): ProjectGitHubRepo

    companion object {

        @ProjectGitHubScope
        @Provides
        fun projectGitHubCache(db: RoomDb): ProjectGitHubCache = ProjectGitHubCache(db)

        @ProjectGitHubScope
        @Provides
        fun projectScopeContainer(app: App): GithubProjectScopeContainer = app
    }
}