package com.paulik.popularlibraries

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class GitHubRepoTest {

    @Mock
    private lateinit var repository: GitHubRepo

    @Before
    fun setUp() {
        MockitoJUnit.rule()
        repository = mock()
    }

    @Test
    fun getUsers_Test() {
        val expectedUsers = listOf(
            UsersGitHubEntity(
                id = 1,
                login = "login1",
                avatarUrl = "avatarUrl",
                nodeId = "nodeId",
                reposUrl = "reposUrl"
            ),
            UsersGitHubEntity(
                id = 3,
                login = "login3",
                avatarUrl = "avatarUrl",
                nodeId = "nodeId",
                reposUrl = "reposUrl"
            )
        )
        `when`(repository.getUsers()).thenReturn(Single.just(expectedUsers))

        val result = repository.getUsers().test()

        verify(repository).getUsers()
        result.assertValueCount(1)
        result.assertValue(expectedUsers)
        result.assertComplete()
        result.assertNoErrors()
    }

    @Test
    fun getProject_Test() {
        val reposUrl = "https://api.github.com/users/mojombo/repos"
        val expectedForks = listOf(
            ProjectGitHubEntity(
                id = 1,
                name = "name1",
                description = "description",
                userId = "userID",
                forksCount = 2,
                forksUrl = "forksUrl",
                private = false
            ),
            ProjectGitHubEntity(
                id = 3,
                name = "name1",
                description = "description",
                userId = "userID",
                forksCount = 2,
                forksUrl = "forksUrl",
                private = true
            )
        )
        `when`(repository.getProject(reposUrl)).thenReturn(Single.just(expectedForks))

        val result = repository.getProject(reposUrl).test()

        verify(repository).getProject(reposUrl)
        result.assertValueCount(1)
        result.assertValue(expectedForks)
        result.assertComplete()
        result.assertNoErrors()
    }

    @Test
    fun getForks_Test() {
        val forksUrl = "https://api.github.com/repos/mojombo/30daysoflaptops.github.io/forks"
        val expectedForks = listOf(
            ForksRepoGitHubEntity(
                id = 1,
                name = "mo",
                fullName = "fullName",
                3
            ),
            ForksRepoGitHubEntity(
                id = 2,
                name = "name",
                fullName = "fullName",
                4
            )
        )
        `when`(repository.getForks(forksUrl)).thenReturn(Single.just(expectedForks))

        val result = repository.getForks(forksUrl).test()

        verify(repository).getForks(forksUrl)
        result.assertValueCount(1)
        result.assertValue(expectedForks)
        result.assertComplete()
        result.assertNoErrors()
    }
}