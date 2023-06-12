package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateUsersList(users: List<UsersGitHubEntity>)

    @AddToEndSingle
    fun showReposUrl(reposUrl: String)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()

    @AddToEndSingle
    fun showErrorMessage(message: String)
}