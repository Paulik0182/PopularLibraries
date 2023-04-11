package com.paulik.popularlibraries.data.cache

import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ForkGitHubCache @Inject constructor(
    private val db: RoomDb
) {

    fun insertForks(forks: List<ForksRepoGitHubEntity>): Single<List<ForksRepoGitHubEntity>> {

        val roomFork = forks.map { fork ->
            ForksRepoGitHubEntity(
                id = fork.id,
                name = fork.name,
                fullName = fork.fullName,
                size = fork.size,
                projectId = fork.projectId
            )
        }
        return db.forksGitHubDao().saveForks(roomFork)
            .toSingle { forks }
    }

    fun getFork(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return db.forksGitHubDao().getAllForks(forksUrl).map {
            it.map { fork ->
                ForksRepoGitHubEntity(
                    id = fork.id,
                    name = fork.name,
                    fullName = fork.fullName,
                    size = fork.size,
                    projectId = fork.projectId
                )
            }
        }
    }
}