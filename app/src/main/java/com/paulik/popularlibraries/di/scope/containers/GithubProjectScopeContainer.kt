package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.GitHubProjectSubcomponent

interface GithubProjectScopeContainer {

    fun initProjectSubcomponent(): GitHubProjectSubcomponent

    fun destroyProjectSubcomponent()
}