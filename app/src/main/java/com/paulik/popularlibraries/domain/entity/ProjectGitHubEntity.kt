package com.paulik.popularlibraries.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProjectGitHubEntity(

    @Expose
    @SerializedName("id")
    private var id: Int,

    @Expose
    @SerializedName("name")
    private val name: String,

    @Expose
    @SerializedName("description")
    private val description: String
)
