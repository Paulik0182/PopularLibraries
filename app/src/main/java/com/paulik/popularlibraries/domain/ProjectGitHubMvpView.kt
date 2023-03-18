package com.paulik.popularlibraries.domain

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ProjectGitHubMvpView : MvpView {

    @AddToEndSingle
    fun updateList(project: List<ProjectGitHubEntity>)

    @AddToEndSingle
    fun showProject(project: String)

    @AddToEndSingle
    fun showProgressBar()

    @AddToEndSingle
    fun hideProgressBar()
}