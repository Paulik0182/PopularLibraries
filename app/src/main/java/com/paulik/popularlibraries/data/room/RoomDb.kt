package com.paulik.popularlibraries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paulik.popularlibraries.domain.dao.UsersGitHubDao
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Database(
    entities = [
        UsersGitHubEntity::class,
    ],
    version = 1, exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun usersGitHubDao(): UsersGitHubDao
}