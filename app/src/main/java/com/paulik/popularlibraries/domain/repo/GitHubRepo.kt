package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single

interface GitHubRepo {

    fun getUsers(): Single<List<UsersGitHubEntity>>
    fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>>
    fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>>
}