package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo

class UsersGitHubRepoImpl : UsersGitHubRepo {

    private val usersGitHub = listOf(
        UsersGitHubEntity(login = "user1"),
        UsersGitHubEntity(login = "user2"),
        UsersGitHubEntity(login = "user3"),
        UsersGitHubEntity(login = "user4"),
        UsersGitHubEntity(login = "user5"),
        UsersGitHubEntity(login = "user6"),
        UsersGitHubEntity(login = "user7")
    )

    override fun getUsers(): List<UsersGitHubEntity> {
        return usersGitHub
    }
}