package com.paulik.popularlibraries.ui.users.forks

import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ForksRepoGitHubMvpView : MvpView {

    fun updateForksList(forks: List<ForksRepoGitHubEntity?>)

    fun showProgressBar()

    fun hideProgressBar()
}