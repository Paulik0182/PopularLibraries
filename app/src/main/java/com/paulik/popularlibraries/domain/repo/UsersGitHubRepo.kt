package com.paulik.popularlibraries.domain.repo

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity

interface UsersGitHubRepo {

    fun getUsers(): List<UsersGitHubEntity>
}