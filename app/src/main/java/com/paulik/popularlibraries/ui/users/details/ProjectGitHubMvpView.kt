package com.paulik.popularlibraries.ui.users.details

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProjectGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateProjectList(project: List<ProjectGitHubEntity?>)

    @AddToEndSingle
    fun showForksRepo(forksUrl: String?)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()
}