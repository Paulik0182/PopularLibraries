package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.GitHubUsersSubcomponent

interface GithubUsersScopeContainer {

    fun initUsersSubcomponent(): GitHubUsersSubcomponent

    fun destroyUsersSubcomponent()
}