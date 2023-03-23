package com.paulik.popularlibraries.di.modules

import android.content.Context
import com.paulik.popularlibraries.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(
    private val app: App
) {

    @Singleton
    @Provides
    fun app(): Context {
        return app
    }
}