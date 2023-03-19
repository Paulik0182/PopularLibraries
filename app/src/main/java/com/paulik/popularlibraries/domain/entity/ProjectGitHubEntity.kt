package com.paulik.popularlibraries.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "projects",
    foreignKeys = [
        ForeignKey(
            entity = UsersGitHubEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE // это каскадное удаление данных, если была удалена сущьность то и все подчиненные связи также удалятся.
        )
    ]
)
data class ProjectGitHubEntity(
    @Expose
    @SerializedName("id")
    @PrimaryKey
    private var id: Int,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private val name: String,

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    private val description: String,

    @Expose
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    private val userId: String,

    @Expose
    @SerializedName("forks_count")
    @ColumnInfo(name = "forks_count")
    private val forksCount: Int,
)
