package com.paulik.popularlibraries.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForksRepoGitHubEntity(

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("full_name")
    val fullName: String,

    @Expose
    @SerializedName("size")
    val size: Int
)