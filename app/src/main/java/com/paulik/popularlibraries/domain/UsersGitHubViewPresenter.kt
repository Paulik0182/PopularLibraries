package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersGitHubViewPresenter : MvpView {

    @AddToEndSingle
    fun updateList(users: List<UsersGitHubEntity>)
}