package com.paulik.popularlibraries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paulik.popularlibraries.data.room.dao.ForksGitHubDao
import com.paulik.popularlibraries.data.room.dao.ProjectGitHubDao
import com.paulik.popularlibraries.data.room.dao.UsersGitHubDao
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Database(
    entities = [
        UsersGitHubEntity::class,
        ProjectGitHubEntity::class,
        ForksRepoGitHubEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun usersGitHubDao(): UsersGitHubDao
    abstract fun projectGitHubDao(): ProjectGitHubDao
    abstract fun forksGitHubDao(): ForksGitHubDao
}