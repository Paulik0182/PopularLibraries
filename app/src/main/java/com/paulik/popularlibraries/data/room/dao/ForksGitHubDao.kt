package com.paulik.popularlibraries.data.room.dao

import androidx.room.*
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface ForksGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(project: ForksRepoGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(forks: List<ForksRepoGitHubEntity>): Completable

    @Query("SELECT * FROM forks WHERE name = :forksUrl")
    fun getAllForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>>

    @Query("SELECT * FROM forks WHERE project_id = :projectId LIMIT 1")
    fun getByName(projectId: String): Maybe<ForksRepoGitHubEntity>

    @Update
    fun update(fork: ForksRepoGitHubEntity): Completable

    @Delete
    fun delete(fork: ForksRepoGitHubEntity): Completable
}