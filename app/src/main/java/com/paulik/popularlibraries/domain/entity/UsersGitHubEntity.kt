package com.paulik.popularlibraries.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.paulik.popularlibraries.ui.users.base.InitParams
import java.util.*

@Entity(tableName = "users") // это название таблицы
data class UsersGitHubEntity(

    @Expose
    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @Expose
    @SerializedName("login")
    @ColumnInfo(name = "login")
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("node_id")
    @ColumnInfo(name = "node_id")
    val nodeId: String,

    @Expose
    @SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    val reposUrl: String
) : InitParams
