package com.paulik.popularlibraries.presenter

import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersGitHubPresenterTest {

    private val testScheduler = TestScheduler()

    @Mock
    private lateinit var mockViewState: UsersGitHubMvpView

    @Mock
    private lateinit var mockUsersGitHubRepo: GitHubRepo

    private lateinit var usersGitHubPresenter: UsersGitHubPresenter

    private lateinit var expectedUsers: List<UsersGitHubEntity>

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable ->
            Schedulers.trampoline()
        }

        expectedUsers = listOf(
            UsersGitHubEntity(
                id = 1,
                login = "mojombo",
                avatarUrl = "avatarUrl",
                nodeId = "nodeId",
                reposUrl = "reposUrl"
            ),
            UsersGitHubEntity(
                id = 3,
                login = "mojombo",
                avatarUrl = "avatarUrl",
                nodeId = "nodeId",
                reposUrl = "reposUrl"
            )
        )
        `when`(mockUsersGitHubRepo.getUsers()).thenReturn(Single.just(expectedUsers))

        usersGitHubPresenter = UsersGitHubPresenter(mockUsersGitHubRepo)
        usersGitHubPresenter.attachView(mockViewState)
    }

    @Test
    fun `test loadData() with success response`() {

        usersGitHubPresenter.loadData()

        verify(mockViewState, atLeastOnce()).showProgressBar()
        verify(mockUsersGitHubRepo, atLeastOnce()).getUsers()

        testScheduler.triggerActions()

        verify(mockViewState, atLeastOnce()).updateUsersList(expectedUsers)
        verify(mockViewState, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun `test loadData() with error response`() {
        val throwable = Throwable("Error response")
        `when`(mockUsersGitHubRepo.getUsers()).thenReturn(Single.error(throwable))

        usersGitHubPresenter.loadData()

        verify(mockViewState, atLeastOnce()).showProgressBar()
        verify(mockUsersGitHubRepo, atLeastOnce()).getUsers()

        testScheduler.triggerActions()

        verify(mockViewState, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun `test onUserClicked()`() {
        val user = UsersGitHubEntity(
            id = 1,
            login = "mojombo",
            avatarUrl = "avatarUrl",
            nodeId = "nodeId",
            reposUrl = "https://api.github.com/users/mojombo/repos"
        )
        usersGitHubPresenter.onUserClicked(user)

        verify(mockViewState).showReposUrl(user.reposUrl)
    }

    @Test
    fun `test backPressed()`() {
        assertTrue(usersGitHubPresenter.backPressed())
    }
}