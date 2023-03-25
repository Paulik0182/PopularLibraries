package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ForksRepoGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(forks: ForksRepoGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(forks: List<ForksRepoGitHubEntity>): Completable

    @Query("SELECT * FROM forks")
    fun getAllForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>>
}