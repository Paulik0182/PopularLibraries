package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.GitHubForksSubcomponent

interface GithubForksScopeContainer {

    fun initForksSubcomponent(): GitHubForksSubcomponent

    fun destroyForksSubcomponent()
}