package com.paulik.popularlibraries.di.modules

import android.content.Context
import androidx.room.Room
import com.paulik.popularlibraries.data.room.RoomDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class DbModule {
    @Singleton
    @Provides
    fun db(context: Context): RoomDb =
        Room.databaseBuilder(context, RoomDb::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build()

//    @Singleton
//    @Provides
//    fun projectGitHubCache(
//        db: RoomDb
//    ): ProjectGitHubCache {
//        return ProjectGitHubCache(db)
//    }
//
//    @Singleton
//    @Provides
//    fun usersGitHubCache(
//        db: RoomDb
//    ): UsersGitHubCache {
//        return UsersGitHubCache(db)
//    }
//
//    @Singleton
//    @Provides
//    fun forkGitHubCache(
//        db: RoomDb
//    ): ForkGitHubCache {
//        return ForkGitHubCache(db)
//    }
}