package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulik.popularlibraries.ui.users.UsersGitHubMvpFragment
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubFragment
import com.paulik.popularlibraries.ui.users.projects.ProjectUserGitHubFragment

interface AppScreens {
    fun usersGitHubScreen(): FragmentScreen
    fun projectsUerGitHubScreen(): FragmentScreen
    fun forksGitHubScreen(): FragmentScreen
}

class AppScreensImpl : AppScreens {

    override fun usersGitHubScreen() = FragmentScreen {
        UsersGitHubMvpFragment()
    }

    override fun projectsUerGitHubScreen() = FragmentScreen {
        ProjectUserGitHubFragment()
    }

    override fun forksGitHubScreen() = FragmentScreen {
        ForksRepoGitHubFragment()
    }
}