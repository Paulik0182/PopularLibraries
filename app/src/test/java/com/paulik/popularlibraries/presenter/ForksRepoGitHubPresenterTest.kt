package com.paulik.popularlibraries.presenter

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenter
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class ForksRepoGitHubPresenterTest {

    private lateinit var presenter: ForksRepoGitHubPresenter
    private val mockView: ForksRepoGitHubMvpView = mock(ForksRepoGitHubMvpView::class.java)
    private val mockGitHubRepo: GitHubRepoImpl = mock(GitHubRepoImpl::class.java)

    private val forksUrl = "https://api.github.com/repos/mojombo/30daysoflaptops.github.io/forks"

    private lateinit var forks: List<ForksRepoGitHubEntity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable ->
            Schedulers.trampoline()
        }

        // положительный ответ от репозитория
        forks = listOf(
            ForksRepoGitHubEntity(
                id = 1,
                name = "mojombo",
                fullName = "mojombo",
                size = 3
            )
        )
        `when`(mockGitHubRepo.getForks(forksUrl)).thenReturn(Single.just(forks))

        presenter = ForksRepoGitHubPresenter(mockGitHubRepo, forksUrl, context.applicationContext)
        presenter.attachView(mockView)
    }

    @Test
    fun `test loadData renders view upon success response`() {

        // запустим метод загрузки данных
        presenter.loadData(forksUrl)

        // убедимся, что показаны индикатор загрузки
        verify(mockView, atLeastOnce()).showProgressBar()

        // убедимся, что метод обновления списка вызван с данными из репозитория
        verify(mockView, atLeastOnce()).updateForksList(forks)

        // убедимся, что прогресс индикатор скрыт
        verify(mockView, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun `test loadData renders view upon failure response`() {
        // отрицательный ответ от репозитория
        val error = Exception()
        `when`(mockGitHubRepo.getForks(forksUrl)).thenReturn(Single.error(error))

        // запустим метод загрузки данных
        presenter.loadData(forksUrl)

        // убедимся, что показаны индикатор загрузки
        verify(mockView, atLeastOnce()).showProgressBar()

        // убедимся, что метод отображения ошибки вызван с переданным объектом-ошибкой
        verify(mockView).showError("Неизвестная ошибка")

        // убедимся, что прогресс индикатор скрыт
        verify(mockView, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun testOnAttach() {
        presenter.onAttach()
    }

    @Test
    fun testOnDetach() {
        presenter.onDetach()
    }
}
