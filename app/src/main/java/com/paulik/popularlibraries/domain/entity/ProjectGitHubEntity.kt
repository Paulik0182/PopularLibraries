package com.paulik.popularlibraries.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProjectGitHubEntity(

    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("user_id")
    val userId: String,

    @Expose
    @SerializedName("forks_count")
    val forksCount: Int,

    @Expose
    @SerializedName("forks_url")
    val forksUrl: String,

    @Expose
    @SerializedName("private")
    val private: Boolean
)
