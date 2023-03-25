package com.paulik.popularlibraries.data.cache

import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersGitHubCache @Inject constructor(
    private val db: RoomDb
) {

    fun insertUsers(users: List<UsersGitHubEntity>): Single<List<UsersGitHubEntity>> {
        val roomUsers = users.map { user ->
            UsersGitHubEntity(
                id = user.id,
                login = user.login,
                reposUrl = user.reposUrl,
                avatarUrl = user.avatarUrl,
                nodeId = user.nodeId
            )
        }
        return db.usersGitHubDao().saveUser(roomUsers)
            .toSingle { users }
    }

    fun getUser(): Single<List<UsersGitHubEntity>> {
        return db.usersGitHubDao().getAllUsers().map {
            it.map { user ->
                UsersGitHubEntity(
                    id = user.id,
                    login = user.login,
                    reposUrl = user.reposUrl,
                    avatarUrl = user.avatarUrl,
                    nodeId = user.nodeId
                )
            }
        }
    }
}