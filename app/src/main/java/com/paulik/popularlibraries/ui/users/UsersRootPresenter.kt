package com.paulik.popularlibraries.ui.users

import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject

class UsersRootPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens
) : MvpPresenter<UsersGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(appScreens.usersGitHubScreen())
    }

    fun backPressed() {
        router.exit()
    }
}