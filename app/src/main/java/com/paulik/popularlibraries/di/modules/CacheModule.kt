package com.paulik.popularlibraries.di.modules

import android.content.Context
import androidx.room.Room
import com.paulik.popularlibraries.data.room.RoomDb
import dagger.Module
import dagger.Provides

private const val DB_NAME = "database.db"

@Module
class CacheModule {

    @Provides
    fun db(context: Context): RoomDb =
        Room.databaseBuilder(context, RoomDb::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build()
}