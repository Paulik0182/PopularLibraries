package com.paulik.popularlibraries.domain.entity

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "forks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectGitHubEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ForksRepoGitHubEntity(

    @Expose
    @SerializedName("id")
    @PrimaryKey
    var id: Int? = 0,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @Expose
    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    var fullName: String? = null,

    @Expose
    @SerializedName("size")
    @ColumnInfo(name = "size")
    var size: Int? = 0,

    @Expose
    @SerializedName("project_id")
    @ColumnInfo(name = "project_id")
    var projectId: Int? = null
) {
    constructor() : this(0)
    constructor(
        id: Int, name: String?, fullName: String?, size: Int, projectId: Int
    ) : this() {
        this.id = id
        this.name = name
        this.fullName = fullName
        this.size = size
        this.projectId = projectId
    }
}