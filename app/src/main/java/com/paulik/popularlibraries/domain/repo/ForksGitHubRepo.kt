package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.core.Single

interface ForksGitHubRepo {

    fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>>
}