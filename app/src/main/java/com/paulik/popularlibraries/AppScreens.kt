package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulik.popularlibraries.ui.users.UsersGitHubMvpFragment
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment

interface AppScreens {
    fun usersGitHubScreen(): FragmentScreen
    fun detailsUerGitHubScreen(): FragmentScreen
}

class AppScreensImpl : AppScreens {

    override fun usersGitHubScreen() = FragmentScreen {
        UsersGitHubMvpFragment()
    }

    override fun detailsUerGitHubScreen() = FragmentScreen {
        DetailsUserGitHubFragment()
    }
}