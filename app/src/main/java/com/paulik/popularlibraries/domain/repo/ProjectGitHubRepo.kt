package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Single

interface ProjectGitHubRepo {

    fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>>
}