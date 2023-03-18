package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateList(users: List<UsersGitHubEntity>)

    @AddToEndSingle
    fun showUser(user: String)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()
}