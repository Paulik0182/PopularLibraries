package com.paulik.popularlibraries.di.modules

import com.paulik.popularlibraries.data.ProjectGitHubRepoImpl
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ProjectRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindHubRepo(impl: ProjectGitHubRepoImpl): ProjectGitHubRepo
}