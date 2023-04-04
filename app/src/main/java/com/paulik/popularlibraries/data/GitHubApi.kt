package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GitHubApi {

    // https://api.github.com/users
    @GET("users")
    fun getUsers(): Single<List<UsersGitHubEntity>>

    // https://api.github.com/users/mojombo/repos
    @GET()
    fun getProject(@Url reposUrl: String?): Single<List<ProjectGitHubEntity>>

    // https://api.github.com/repos/mojombo/30daysoflaptops.github.io/forks
    @GET()
    fun getForks(@Url forksUrl: String?): Single<List<ForksRepoGitHubEntity>>
}