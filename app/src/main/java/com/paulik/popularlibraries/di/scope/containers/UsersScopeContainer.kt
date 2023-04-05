package com.paulik.popularlibraries.di.scope.containers

import com.paulik.popularlibraries.di.components.UsersRepositorySubcomponent

interface UsersScopeContainer {

    fun initUsersRepositorySubcomponent(): UsersRepositorySubcomponent

    fun destroyUsersRepositorySubcomponent()
}