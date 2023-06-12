package com.paulik.popularlibraries

import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenter
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`

class ForksRepoGitHubPresenterTest {

    private lateinit var presenter: ForksRepoGitHubPresenter
    private val mockView: ForksRepoGitHubMvpView = mock(ForksRepoGitHubMvpView::class.java)
    private val mockGitHubRepo: GitHubRepoImpl = mock(GitHubRepoImpl::class.java)

    private val forksUrl = "https://api.github.com/repos/mojombo/30daysoflaptops.github.io/forks"

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable ->
            Schedulers.trampoline()
        }
        presenter = ForksRepoGitHubPresenter(mockGitHubRepo, forksUrl)
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        presenter.detachView(mockView)
    }

    @Test
    fun `test loadData renders view upon success response`() {
        // положительный ответ от репозитория
        val forks = listOf(
            ForksRepoGitHubEntity(
                id = 1,
                name = "mojombo",
                fullName = "mojombo",
                size = 3
            )
        )
        `when`(mockGitHubRepo.getForks(forksUrl)).thenReturn(Single.just(forks))

        // запустим метод загрузки данных
        presenter.loadData(forksUrl)

        // убедимся, что показаны индикатор загрузки
        verify(mockView).showProgressBar()

        // убедимся, что метод обновления списка вызван с данными из репозитория
        verify(mockView).updateForksList(forks)

        // убедимся, что прогресс индикатор скрыт
        verify(mockView, times(2)).hideProgressBar()
    }

    @Test
    fun `test loadData renders view upon failure response`() {
        // отрицательный ответ от репозитория
        val error = Exception()
        `when`(mockGitHubRepo.getForks(forksUrl)).thenReturn(Single.error(error))

        // запустим метод загрузки данных
        presenter.loadData(forksUrl)

        // убедимся, что показаны индикатор загрузки
        verify(mockView).showProgressBar()

        // убедимся, что метод отображения ошибки вызван с переданным объектом-ошибкой
        verify(mockView).showError("Неизвестная ошибка")

        // убедимся, что прогресс индикатор скрыт
        verify(mockView).hideProgressBar()
    }
}
