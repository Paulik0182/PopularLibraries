package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.ForksGitHubModule
import com.paulik.popularlibraries.di.scope.ForksGitHubScope
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenter
import dagger.Subcomponent

@ForksGitHubScope
@Subcomponent(
    modules = [
        ForksGitHubModule::class
    ]
)
interface GitHubForksSubcomponent {

    fun forksRepoGitHubPresenter(): ForksRepoGitHubPresenter
}