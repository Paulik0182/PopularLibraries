package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity

@Dao
interface ProjectGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(project: ProjectGitHubEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(projects: List<ProjectGitHubEntity>)

    @Query("SELECT * FROM projects") // запрос всех проектов. projects - это название таблицы
    fun getAllProject(): List<ProjectGitHubEntity>

    @Query("SELECT * FROM projects WHERE userId = :userId LIMIT 1") // запрос всех проектов где проект совпадает с переданным проекта и ограничить возвращаемых запросов до 1
    fun getByUserId(userId: String): ProjectGitHubEntity?
}