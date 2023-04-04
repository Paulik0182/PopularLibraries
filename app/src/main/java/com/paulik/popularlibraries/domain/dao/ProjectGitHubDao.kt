package com.paulik.popularlibraries.domain.dao

import androidx.room.*
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface ProjectGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProject(project: ProjectGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProject(projects: List<ProjectGitHubEntity>): Completable

    @Query("SELECT * FROM projects WHERE name = :reposUrl")
    fun getAllProject(reposUrl: String): Single<List<ProjectGitHubEntity>>

    @Query("SELECT * FROM projects WHERE user_Id = :userId LIMIT 1")
    fun getByUserId(userId: String): Maybe<ProjectGitHubEntity>

    @Update
    fun update(project: ProjectGitHubEntity): Completable

    @Delete
    fun delete(project: ProjectGitHubEntity): Completable
}