package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GitHubRepoImpl(
    private val gitHubApi: GitHubApi
) : GitHubRepo {

    override fun getUsers(): Single<List<UsersGitHubEntity>> {
        return gitHubApi.getUsers()
    }

    override fun getProject(user: String): Single<List<ProjectGitHubEntity>> {
        return gitHubApi.getProject(user)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}