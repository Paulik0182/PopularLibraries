package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulik.popularlibraries.ui.users.UsersGitHubMvpFragment
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment

object AppScreens {

    fun usersGitHubScreen() = FragmentScreen {
        UsersGitHubMvpFragment()
    }

    fun DetailsUerGitHubScreen() = FragmentScreen {
        DetailsUserGitHubFragment()
    }
}