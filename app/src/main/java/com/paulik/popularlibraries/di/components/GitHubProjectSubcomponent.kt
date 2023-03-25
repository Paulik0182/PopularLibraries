package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.GitHubRepoModule
import com.paulik.popularlibraries.di.scope.GitHubScope
import dagger.Subcomponent

@GitHubScope
@Subcomponent(
    modules = [
        GitHubRepoModule::class
    ]
)
interface GitHubProjectSubcomponent {

//    fun detailsUserGitHubPresenterFactory(): DetailsUserGitHubPresenterFactory
}