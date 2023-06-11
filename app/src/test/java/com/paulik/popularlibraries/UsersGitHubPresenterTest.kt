package com.paulik.popularlibraries

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
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.times
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

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable ->
            Schedulers.trampoline()
        }
        usersGitHubPresenter = UsersGitHubPresenter(mockUsersGitHubRepo)
        usersGitHubPresenter.attachView(mockViewState)
        reset(mockUsersGitHubRepo)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
        usersGitHubPresenter.detachView(mockViewState)
    }

    @Test
    fun `test loadData() with success response`() {
        val expectedUsers = listOf(
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

        usersGitHubPresenter.loadData()

        verify(mockViewState).showProgressBar()
        verify(mockUsersGitHubRepo).getUsers()

        testScheduler.triggerActions()

        verify(mockViewState).updateUsersList(expectedUsers)
        verify(mockViewState, times(2)).hideProgressBar()
    }

    @Test
    fun `test loadData() with error response`() {
        val throwable = Throwable("Error response")
        `when`(mockUsersGitHubRepo.getUsers()).thenReturn(Single.error(throwable))

        usersGitHubPresenter.loadData()

        verify(mockViewState, atLeastOnce()).showProgressBar()
        verify(mockUsersGitHubRepo).getUsers()

        testScheduler.triggerActions()

        val message = throwable.message
        if (message != null) {
            verify(mockViewState).showErrorMessage(message)
        } else {
            verify(mockViewState).showErrorMessage("Неизвестная ошибка")
        }
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