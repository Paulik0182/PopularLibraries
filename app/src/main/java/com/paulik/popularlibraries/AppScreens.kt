package com.paulik.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun usersGitHubScreen(): FragmentScreen
    fun projectsUerGitHubScreen(reposUrl: String): FragmentScreen
    fun forksGitHubScreen(forksUrl: String?): FragmentScreen
}