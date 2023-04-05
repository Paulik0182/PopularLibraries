package com.paulik.popularlibraries.ui.users.forks

import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ForksRepoGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateForksList(forks: List<ForksRepoGitHubEntity>)

    @AddToEndSingle
    fun showFork(fork: String)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()
}