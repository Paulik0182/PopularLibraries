package com.paulik.popularlibraries

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.data.users.GitHubApi
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    // обращение к API github через пакет OkHttp (с помощью этой библиотеки делаются запросы и получаем ответы)
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(UsedConst.settingTimeConst.ITEM_OUT_CONST, TimeUnit.SECONDS)
        .build()

    // для работы с пришедшими данными
    private fun getGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(UsedConst.httpsConst.GIT_CONST)
        .client(okHttpClient) // увеличение времени по таймауту
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // возможность оборачивать объекты в RxJava, например в single.
        .addConverterFactory(GsonConverterFactory.create(getGson())) // это приобразователь объектов из одного типа в другой тип (здесь старонняя библиотека)
        .build()

    val gitHubApi: GitHubApi =
        retrofit.create(GitHubApi::class.java) //создаем gitHubApi. Автоматически обратится к интерфейсу

    // Вариант создания GitHubApi
//    val retrofitService by lazy {
//        retrofit
//            .create<GitHubApi>()
//    }


    // Создаем Instance Cicerone
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        _instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    val networkStatusInteractor: NetworkStatusInteractor by lazy {
        NetworkStatusInteractorImpl()
    }
}

interface IContextProvider {
    val context: Context
}

object ContextProvider : IContextProvider {

    override val context: Context
        get() = App.context

}