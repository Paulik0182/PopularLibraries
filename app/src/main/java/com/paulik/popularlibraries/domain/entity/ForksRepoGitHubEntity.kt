package com.paulik.popularlibraries.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paulik.popularlibraries.ui.users.base.InitParams

@Entity(
    tableName = "forks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectGitHubEntity::class,
            parentColumns = ["id"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE // это каскадное удаление данных, если была удалена сущьность то и все подчиненные связи также удалятся.
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
) : InitParams