package com.paulik.popularlibraries.di.modules

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paulik.popularlibraries.UsedConst
import com.paulik.popularlibraries.data.GitHubApi
import com.paulik.popularlibraries.data.connectivity.NetworkStatusInteractorImpl
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "BASE_URL"

@Module
class NetworkModule {
    @Singleton
    @Provides
    @Named(BASE_URL)
    fun baseUrl(): String {
        return UsedConst.httpsConst.GIT_CONST
    }

    // обращение к API github через пакет OkHttp (с помощью этой библиотеки делаются запросы и получаем ответы)
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(UsedConst.settingTimeConst.ITEM_OUT_CONST, TimeUnit.SECONDS)
        .build()

    // для работы с пришедшими данными
    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Singleton
    @Provides
    fun getRetrofit(
        @Named(BASE_URL) baseUrl: String,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient) // увеличение времени по таймауту
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // возможность оборачивать объекты в RxJava, например в single.
        .addConverterFactory(GsonConverterFactory.create(gson)) // это приобразователь объектов из одного типа в другой тип (здесь старонняя библиотека)
        .build()

    @Singleton
    @Provides
    fun gitHubApi(
        retrofit: Retrofit
    ): GitHubApi {
        return retrofit.create<GitHubApi>()
    }

    @Singleton
    @Provides
    fun networkStatus(
        context: Context
    ): NetworkStatusInteractor {
        return NetworkStatusInteractorImpl(context)
    }
}