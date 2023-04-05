package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.ProjectRepositorySubcomponent

interface ProjectScopeContainer {

    fun initProjectRepositorySubcomponent(): ProjectRepositorySubcomponent

    fun destroyProjectRepositorySubcomponent()
}