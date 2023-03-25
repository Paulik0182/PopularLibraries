package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UsersGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // в скобках сказано,что если есть такой пользователь Id то перезаписываем данные. Есть варианты)
    fun saveUser(user: UsersGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(users: List<UsersGitHubEntity>): Completable

    @Query("SELECT * FROM users") // запрос всех пользователей. users - это название таблицы
//    fun getAllUsers(): Observable<List<UsersGitHubEntity>> // постоянно будет возвращать значения при изменении
    fun getAllUsers(): Single<List<UsersGitHubEntity>>

    @Query("SELECT * FROM users WHERE login = :login LIMIT 1") // запрос всех пользователей где пользователь совпадает с переданным пользователем и ограничить возвращаемых запросов до 1
    fun getByLogin(login: String): Single<UsersGitHubEntity>
}