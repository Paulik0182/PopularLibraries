package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.users.GitHubApi
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class UsersGitHubRepoImpl(
    private val gitHubApi: GitHubApi
) : UsersGitHubRepo {

    override fun getUsers(): Single<List<UsersGitHubEntity>> {
        return gitHubApi.getUsers()
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}