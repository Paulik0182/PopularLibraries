package com.paulik.popularlibraries.ui.users.forks

import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import moxy.MvpPresenter

class ForksRepoRootPresenter(
    private val router: Router
) : MvpPresenter<ProjectGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

//        router.replaceScreen(AppScreens.detailsUerGitHubScreen())
    }

    fun backPressed() {
        router.exit()
    }
}