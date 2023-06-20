package com.paulik.popularlibraries.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
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

    // https://api.github.com/search/users?q=mojombo
    @GET("search/users")
    fun searchUsers(@Query("q") query: String?): Single<SearchUsersDto>
}

data class SearchUsersDto(
    @Expose
    @SerializedName("items")
    val items: List<UsersGitHubEntity>
)