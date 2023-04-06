package com.paulik.popularlibraries.ui.users

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersGitHubMvpView : MvpView {

    fun updateUsersList(users: List<UsersGitHubEntity>)

    fun showReposUrl(reposUrl: String)

    fun showProgressBar()

    fun hideProgressBar()
}