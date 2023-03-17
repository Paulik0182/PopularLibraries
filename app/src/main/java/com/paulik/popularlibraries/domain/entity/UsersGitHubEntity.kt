package com.paulik.popularlibraries.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class UsersGitHubEntity(

    val id: Int = UUID.randomUUID().variant(),

    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
)
