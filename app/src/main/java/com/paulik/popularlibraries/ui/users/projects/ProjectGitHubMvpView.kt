package com.paulik.popularlibraries.ui.users.projects

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
@AddToEndSingle
interface ProjectGitHubMvpView : MvpView {

    fun updateProjectList(project: List<ProjectGitHubEntity?>)

    fun showForksRepo(forksUrl: String?)

    fun showProgressBar()

    fun hideProgressBar()
}