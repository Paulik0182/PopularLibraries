package com.paulik.popularlibraries.data.cache

import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import io.reactivex.rxjava3.core.Single

class ProjectGitHubCache(
    private val db: RoomDb
) {

    fun insertProject(projects: List<ProjectGitHubEntity>): Single<List<ProjectGitHubEntity>> {

        val roomProject = projects.map { project ->
            ProjectGitHubEntity(
                id = project.id,
                name = project.name,
                description = project.description,
                userId = project.userId,
                forksCount = project.forksCount,
                forksUrl = project.forksUrl,
                private = project.private
            )
        }
        return db.projectGitHubDao().saveProject(roomProject)
            .toSingle { projects }
    }

    fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
        return db.projectGitHubDao().getAllProject(reposUrl).map {
            it.map { project ->
                ProjectGitHubEntity(
                    id = project.id,
                    name = project.name,
                    description = project.description,
                    userId = project.userId,
                    forksCount = project.forksCount,
                    forksUrl = project.forksUrl,
                    private = project.private
                )
            }
        }
    }
}