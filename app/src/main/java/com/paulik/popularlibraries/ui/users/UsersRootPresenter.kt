package com.paulik.popularlibraries.ui.users

import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.AppScreens
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import moxy.MvpPresenter

class UsersRootPresenter(
    private val router: Router
) : MvpPresenter<UsersGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersGitHubScreen())
    }

    fun backPressed() {
        router.exit()
    }
}