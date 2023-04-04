package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface ProjectGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(project: ProjectGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(projects: List<ProjectGitHubEntity>): Completable

    @Query("SELECT * FROM projects WHERE name = :reposUrl")
    fun getAllProject(reposUrl: String): Single<List<ProjectGitHubEntity>>

    @Query("SELECT * FROM projects WHERE user_Id = :userId LIMIT 1") // запрос всех проектов где проект совпадает с переданным проекта и ограничить возвращаемых запросов до 1
    fun getByName(userId: String): Maybe<ProjectGitHubEntity>
}