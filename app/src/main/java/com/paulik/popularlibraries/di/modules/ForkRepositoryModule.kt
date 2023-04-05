package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ForkGitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ForkRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindHubRepo(impl: ForkGitHubRepoImpl): ForksGitHubRepo
}