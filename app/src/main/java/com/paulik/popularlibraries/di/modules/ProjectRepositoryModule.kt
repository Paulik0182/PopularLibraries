package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.ProjectGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.ProjectScope
import com.paulik.popularlibraries.di.scope.containers.ProjectScopeContainer
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ProjectRepositoryModule {

    @ProjectScope
    @Binds
    abstract fun bindHubRepo(impl: ProjectGitHubRepoImpl): ProjectGitHubRepo

    companion object {
        @ProjectScope
        @Provides
        fun projectGitHubCache(db: RoomDb): ProjectGitHubCache = ProjectGitHubCache(db)

        @ProjectScope
        @Provides
        fun scopeContainer(app: App): ProjectScopeContainer = app
    }
}