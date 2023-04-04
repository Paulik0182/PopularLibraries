package com.paulik.popularlibraries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paulik.popularlibraries.domain.dao.ForksRepoGitHubDao
import com.paulik.popularlibraries.domain.dao.ProjectGitHubDao
import com.paulik.popularlibraries.domain.dao.UsersGitHubDao
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Database(
    entities = [
        UsersGitHubEntity::class,
        ProjectGitHubDao::class,
        ForksRepoGitHubDao::class
    ],
    version = 1, exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun usersGitHubDao(): UsersGitHubDao
    abstract fun projectGitHubDao(): ProjectGitHubDao
    abstract fun forksGitHubDao(): ForksRepoGitHubDao
}