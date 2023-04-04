package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.ProjectGitHubModule
import com.paulik.popularlibraries.di.scope.ProjectGitHubScope
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubPresenter
import dagger.Subcomponent

@ProjectGitHubScope
@Subcomponent(
    modules = [
        ProjectGitHubModule::class
    ]
)
interface GitHubProjectSubcomponent {

    fun forksSubcomponent(): GitHubForksSubcomponent

    fun detailsUserGitHubPresenter(reposUrl: String): DetailsUserGitHubPresenter
}