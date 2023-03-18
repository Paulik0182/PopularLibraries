package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GitHubApi {

    @GET("users")
    fun getUsers(): Single<List<UsersGitHubEntity>>

    @GET("users/{user}/repos")
    fun getProject(@Path("user") user: String?): Single<List<ProjectGitHubEntity>>

    @GET("users/{user}/repos")
    fun getProject2(@Url reposUrl: String?): Single<List<ProjectGitHubEntity>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String?): Single<UsersGitHubEntity>
}