package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.ForkRepositoryModule
import com.paulik.popularlibraries.di.scope.ForkScope
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenterFactory
import dagger.Subcomponent

@ForkScope
@Subcomponent(
    modules = [
        ForkRepositoryModule::class
    ]
)
interface ForkRepositorySubcomponent {

    fun forksRepoGitHubPresenterFactory(): ForksRepoGitHubPresenterFactory
}