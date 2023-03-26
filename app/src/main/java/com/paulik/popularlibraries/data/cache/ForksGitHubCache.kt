package com.paulik.popularlibraries.data.cache

import com.paulik.popularlibraries.data.room.RoomDb

class ForksGitHubCache(
    private val db: RoomDb
) {

//    fun insertForks(forks: List<ForksRepoGitHubEntity>): Single<List<ForksRepoGitHubEntity>> {
//        val roomForks = forks.map { fork ->
//            ForksRepoGitHubEntity(
//                id =
//            )
//        }
//        return db.ForksRepoGitHubDao().saveForks(roomForks)
//            .toSingle { forks }
//    }
//
//    fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
//        return db.ForksRepoGitHubDao().getAllForks(forksUrl).map {
//            it.map { fork ->
//                ForksRepoGitHubEntity(
//                    id =
//                )
//            }
//        }
//    }
}