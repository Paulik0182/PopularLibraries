package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.ProjectRepositoryModule
import com.paulik.popularlibraries.di.scope.ProjectScope
import com.paulik.popularlibraries.ui.users.projects.ProjectsUserGitHubPresenterFactory
import dagger.Subcomponent

@ProjectScope
@Subcomponent(
    modules = [
        ProjectRepositoryModule::class
    ]
)
interface ProjectRepositorySubcomponent {

    fun forkRepositorySubcomponent(): ForkRepositorySubcomponent
    fun projectUserGitHubPresenterFactory(): ProjectsUserGitHubPresenterFactory
}