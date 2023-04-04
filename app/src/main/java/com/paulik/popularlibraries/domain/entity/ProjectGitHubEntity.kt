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
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE // это каскадное удаление данных, если была удалена сущьность то и все подчиненные связи также удалятся.
        )
    ]
)
data class ProjectGitHubEntity(

    @Expose
    @SerializedName("id")
    @PrimaryKey
    var id: Int? = 0,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null,

    @Expose
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    var userId: String? = null,

    @Expose
    @SerializedName("forks_count")
    @ColumnInfo(name = "forks_count")
    var forksCount: Int? = 0,

    @Expose
    @SerializedName("forks_url")
    @ColumnInfo(name = "forks_url")
    var forksUrl: String? = null,

    @Expose
    @SerializedName("private")
    @ColumnInfo(name = "private")
    var private: Boolean? = false,
) {
    constructor() : this(0)
    constructor(
        id: Int, name: String?, description: String?,
        userId: String, forksCount: Int, forksUrl: String?, private: Boolean
    ) : this() {
        this.id = id
        this.name = name
        this.description = description
        this.userId = userId
        this.forksCount = forksCount
        this.forksUrl = forksUrl
        this.private = private
    }
}
