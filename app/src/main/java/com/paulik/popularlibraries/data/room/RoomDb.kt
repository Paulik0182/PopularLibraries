package com.paulik.popularlibraries.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paulik.popularlibraries.domain.dao.ProjectGitHubDao
import com.paulik.popularlibraries.domain.dao.UsersGitHubDao
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Database(
    entities = [
        UsersGitHubEntity::class,
        ProjectGitHubDao::class,
    ],
    version = 1, exportSchema = false
)
abstract class RoomDb : RoomDatabase() {

    abstract fun usersGitHubDao(): UsersGitHubDao
    abstract fun projectGitHubDao(): ProjectGitHubDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context): RoomDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }


//        private const val DB_NAME = "database.db"
//        val instanceRoom by lazy {
//            Room.databaseBuilder(App.instance, RoomDb::class.java, DB_NAME)
//                .allowMainThreadQueries()
//                .build()
//        }
    }
}