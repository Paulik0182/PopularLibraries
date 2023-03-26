package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.base.InitParams
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateUsersList(users: List<UsersGitHubEntity>)

    @AddToEndSingle
    fun showReposUrl(reposUrl: InitParams)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()
}