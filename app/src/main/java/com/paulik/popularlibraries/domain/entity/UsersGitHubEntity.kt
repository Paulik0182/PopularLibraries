package com.paulik.popularlibraries.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class UsersGitHubEntity(

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("node_id")
    val nodeId: String,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String,
)
