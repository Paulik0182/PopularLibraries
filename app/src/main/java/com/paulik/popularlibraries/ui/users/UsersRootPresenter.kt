package com.paulik.popularlibraries.ui.users

import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.AppScreens
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import moxy.MvpPresenter
import javax.inject.Inject

class UsersRootPresenter : MvpPresenter<UsersGitHubMvpView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appScreens: AppScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(appScreens.usersGitHubScreen())
    }

    fun backPressed() {
        router.exit()
    }
}