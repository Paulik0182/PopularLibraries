package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulik.popularlibraries.ui.users.UsersGitHubMvpFragment
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubFragment
import com.paulik.popularlibraries.ui.users.projects.ProjectUserGitHubFragment

class AppScreensImpl : AppScreens {

    override fun usersGitHubScreen() = FragmentScreen {
        UsersGitHubMvpFragment.newInstance()
    }

    override fun projectsUerGitHubScreen(reposUrl: String) = FragmentScreen {
        ProjectUserGitHubFragment.newInstance(reposUrl)
    }

    override fun forksGitHubScreen(forksUrl: String?) = FragmentScreen {
        ForksRepoGitHubFragment.newInstance(forksUrl)
    }
}