package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.UsersRepositoryModule
import com.paulik.popularlibraries.di.scope.UsersScope
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersRepositoryModule::class
    ]
)
interface UsersRepositorySubcomponent {

    fun projectRepositorySubcomponent(): ProjectRepositorySubcomponent

    fun usersGitHubPresenter(): UsersGitHubPresenter
}