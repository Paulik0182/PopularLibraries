package com.paulik.popularlibraries.data.cache

import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
import io.reactivex.rxjava3.core.Single

class ForksGitHubCache(
    private val db: RoomDb
) {

    fun insertForks(forks: List<ForksRepoGitHubEntity>): Single<List<ForksRepoGitHubEntity>> {
        val roomForks = forks.map { fork ->
            ForksRepoGitHubEntity(
                id = fork.id,
                name = fork.name,
                fullName = fork.fullName,
                size = fork.size
            )
        }
        return db.forksGitHubDao().saveForks(roomForks)
            .toSingle { forks }
    }

    fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return db.forksGitHubDao().getAllForks(forksUrl).map {
            it.map { fork ->
                ForksRepoGitHubEntity(
                    id = fork.id,
                    name = fork.name,
                    fullName = fork.fullName,
                    size = fork.size
                )
            }
        }
    }
}