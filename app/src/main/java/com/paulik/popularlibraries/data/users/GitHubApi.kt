package com.paulik.popularlibraries.data.users

import com.paulik.popularlibraries.domain.entity.GitProjectEntity
import com.paulik.popularlibraries.domain.entity.GitUserEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users")
    fun getUsers(): Single<List<UsersGitHubEntity>>

    @GET("users/{user}/repos")
    fun getProject(@Path("user") user: String?): Single<List<GitProjectEntity>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Single<GitUserEntity>
}