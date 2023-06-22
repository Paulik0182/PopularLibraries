package com.paulik.popularlibraries.presenter

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.MOJOMBO_FORKS_API_URL
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
    private var view = mock(ForksRepoGitHubMvpView::class.java)
    private val mockGitHubRepo = mock(GitHubRepoImpl::class.java)

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
        `when`(mockGitHubRepo.getForks(MOJOMBO_FORKS_API_URL)).thenReturn(Single.just(forks))

        presenter = ForksRepoGitHubPresenter(
            mockGitHubRepo,
            MOJOMBO_FORKS_API_URL,
            context.applicationContext
        )
        presenter.attachView(view)
    }

    @Test
    fun `test loadData renders view upon success response`() {

        // запустим метод загрузки данных
        presenter.loadData(MOJOMBO_FORKS_API_URL)

        // убедимся, что показаны индикатор загрузки
        verify(view, atLeastOnce()).showProgressBar()

        // убедимся, что метод обновления списка вызван с данными из репозитория
        verify(view, atLeastOnce()).updateForksList(forks)

        // убедимся, что прогресс индикатор скрыт
        verify(view, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun `test loadData renders view upon failure response`() {
        // отрицательный ответ от репозитория
        val error = Exception()
        `when`(mockGitHubRepo.getForks(MOJOMBO_FORKS_API_URL)).thenReturn(Single.error(error))

        // запустим метод загрузки данных
        presenter.loadData(MOJOMBO_FORKS_API_URL)

        // убедимся, что показаны индикатор загрузки
        verify(view, atLeastOnce()).showProgressBar()

        // убедимся, что метод отображения ошибки вызван с переданным объектом-ошибкой
        verify(view).showError("Неизвестная ошибка")

        // убедимся, что прогресс индикатор скрыт
        verify(view, atLeastOnce()).hideProgressBar()
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