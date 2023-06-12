package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Single

interface ProjectGitHubRepo {

    fun getProject(user: String): Single<List<ProjectGitHubEntity>>
}