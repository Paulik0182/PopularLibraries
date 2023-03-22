package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

@Dao
interface UsersGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой пользователь Id то перезаписываем данные. Есть варианты)
    fun saveUser(user: UsersGitHubEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(users: List<UsersGitHubEntity>)

    @Query("SELECT * FROM users") // запрос всех пользователей. users - это название таблицы
    fun getAllUsers(): List<UsersGitHubEntity>

    @Query("SELECT * FROM users WHERE login = :login LIMIT 1") // запрос всех пользователей где пользователь совпадает с переданным пользователем и ограничить возвращаемых запросов до 1
    fun getByLogin(login: String): UsersGitHubEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(project: ProjectGitHubEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой проект-Id то перезаписываем данные. Есть варианты)
    fun saveProject(projects: List<ProjectGitHubEntity>)

    @Query("SELECT * FROM projects") // запрос всех проектов. projects - это название таблицы
    fun getAllProject(): List<ProjectGitHubEntity>
}