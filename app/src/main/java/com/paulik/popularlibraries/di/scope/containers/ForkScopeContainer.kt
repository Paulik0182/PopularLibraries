package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.ForkRepositorySubcomponent

interface ForkScopeContainer {

    fun initForkRepositorySubcomponent(): ForkRepositorySubcomponent

    fun destroyForkRepositorySubcomponent()
}