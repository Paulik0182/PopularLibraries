package com.paulik.popularlibraries.domain.entity.forks

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity

@Entity(
    tableName = "forks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectGitHubEntity::class,
            parentColumns = ["name"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ForksRepoGitHubEntity(

    @Expose
    @SerializedName("id")
    var id: Int? = 0,

    @Expose
    @SerializedName("name")
    var name: String? = null,

    @Expose
    @SerializedName("full_name")
    var fullName: String? = null,

    @Expose
    @SerializedName("size")
    var size: Int? = 0,
) {
    constructor() : this(0)
    constructor(
        id: Int, name: String?, fullName: String?, size: Int
    ) : this() {
        this.id = id
        this.name = name
        this.fullName = fullName
        this.size = size
    }
}