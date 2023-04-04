package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.UsersGitHubModule
import com.paulik.popularlibraries.di.scope.UsersGitHubScope
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import dagger.Subcomponent

@UsersGitHubScope
@Subcomponent(
    modules = [
        UsersGitHubModule::class
    ]
)
interface GitHubUsersSubcomponent {

    fun projectSubcomponent(): GitHubProjectSubcomponent

    fun usersGitHubPresenter(): UsersGitHubPresenter
}