package com.paulik.popularlibraries.domain.dao

import androidx.room.*
import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface ForksRepoGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(forks: ForksRepoGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveForks(forks: List<ForksRepoGitHubEntity>): Completable

    @Query("SELECT * FROM forks WHERE name = :forksUrl")
    fun getAllForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>>

    @Query("SELECT * FROM forks WHERE name = :nameProject LIMIT 1")
    fun getByNameProject(nameProject: String): Maybe<ProjectGitHubEntity>

    @Update
    fun update(forks: ForksRepoGitHubEntity): Completable

    @Delete
    fun delete(forks: ForksRepoGitHubEntity): Completable
}