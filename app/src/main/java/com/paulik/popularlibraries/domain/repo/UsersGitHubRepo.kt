package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single

interface UsersGitHubRepo {

    fun getUsers(): Single<List<UsersGitHubEntity>>
}