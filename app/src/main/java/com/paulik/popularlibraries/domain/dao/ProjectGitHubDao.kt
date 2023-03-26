package com.paulik.popularlibraries.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProjectGitHubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProject(project: ProjectGitHubEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProject(projects: List<ProjectGitHubEntity>): Completable

    @Query("SELECT * FROM projects")
    fun getAllProject(reposUrl: String): Single<List<ProjectGitHubEntity>>

//    @Query("SELECT * FROM projects WHERE userId = :userId LIMIT 1")
//    fun getByUserId(userId: String): Maybe<ProjectGitHubEntity>
}