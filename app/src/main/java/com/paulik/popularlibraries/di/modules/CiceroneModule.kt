package com.paulik.popularlibraries.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.AppScreens
import com.paulik.popularlibraries.AppScreensImpl
import dagger.Module
import dagger.Provides

@Module
class CiceroneModule {

    // Создаем Instance Cicerone
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    @Provides
    fun navigationHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    fun router(): Router {
        return cicerone.router
    }

    @Provides
    fun AppScreens(): AppScreens {
        return AppScreensImpl()
    }
}