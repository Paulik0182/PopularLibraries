package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
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