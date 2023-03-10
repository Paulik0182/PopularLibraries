package com.paulik.popularlibraries.domain

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface UsersGitHubViewPresenter : MvpView {

    @AddToEndSingle
    fun updateList()
}