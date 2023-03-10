package com.paulik.popularlibraries.domain.entity

import java.util.*

data class UsersGitHubEntity(
    val id: String = UUID.randomUUID().toString(),
    val login: String
)
