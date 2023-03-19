package com.paulik.popularlibraries.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.domain.dao.ProjectGitHubDao
import com.paulik.popularlibraries.domain.dao.UsersGitHubDao
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Database(
    entities = [
        UsersGitHubEntity::class,
        ProjectGitHubDao::class
    ],
    version = 1, exportSchema = false
)
abstract class RoomDb() : RoomDatabase() {

    abstract val usersGitHubDao: UsersGitHubDao
    abstract val projectGitHubDao: ProjectGitHubDao

    companion object {
        private const val DB_NAME = "database.db"
        val instanceRoom by lazy {
            Room.databaseBuilder(App.instance, RoomDb::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}