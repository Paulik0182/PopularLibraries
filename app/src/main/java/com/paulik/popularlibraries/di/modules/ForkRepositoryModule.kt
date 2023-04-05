package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ForkGitHubRepoImpl
import com.paulik.popularlibraries.data.cache.ForkGitHubCache
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.di.scope.ForkScope
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ForkRepositoryModule {

    @ForkScope
    @Binds
    abstract fun bindHubRepo(impl: ForkGitHubRepoImpl): ForksGitHubRepo

    companion object {
        @ForkScope
        @Provides
        fun forkGitHubCache(db: RoomDb): ForkGitHubCache = ForkGitHubCache(db)
    }
}