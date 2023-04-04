package com.paulik.popularlibraries.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "forks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectGitHubEntity::class,
            parentColumns = ["forksUrl"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
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