package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.GitHubRepoModule
import com.paulik.popularlibraries.di.scope.GitHubScope
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import dagger.Subcomponent

@GitHubScope
@Subcomponent(
    modules = [
        GitHubRepoModule::class
    ]
)
interface GitHubUsersSubcomponent {

//    fun projectSubcomponent(): GitHubProjectSubcomponent

    fun usersGitHubPresenter(): UsersGitHubPresenter
}