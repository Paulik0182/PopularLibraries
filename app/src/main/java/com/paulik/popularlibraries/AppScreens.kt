package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulik.popularlibraries.ui.users.UsersGitHubFragment
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment

object AppScreens {

    fun usersGitHubScreen() = FragmentScreen {
        UsersGitHubFragment()
    }

    fun DetailsUerGitHubScreen() = FragmentScreen {
        DetailsUserGitHubFragment()
    }
}