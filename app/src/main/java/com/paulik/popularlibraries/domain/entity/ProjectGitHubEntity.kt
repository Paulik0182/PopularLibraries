package com.paulik.popularlibraries.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paulik.popularlibraries.ui.users.base.InitParams

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
    var id: Int,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String,

    @Expose
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    val userId: String,

    @Expose
    @SerializedName("forks_count")
    @ColumnInfo(name = "forks_count")
    val forksCount: Int,

    @Expose
    @SerializedName("forks_url")
    @ColumnInfo(name = "forks_url")
    val forksUrl: String,

    @Expose
    @SerializedName("private")
    @ColumnInfo(name = "private")
    val private: Boolean
) : InitParams
