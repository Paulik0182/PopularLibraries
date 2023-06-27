package com.paulik.popularlibraries.presenter

import android.content.Context
import android.widget.Toast
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.paulik.popularlibraries.MOJOMBO_REPOS_API_URL
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubPresenter
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class DetailsUserGitHubPresenterTest {

    private lateinit var presenter: DetailsUserGitHubPresenter
    private val mockView: ProjectGitHubMvpView = mock(ProjectGitHubMvpView::class.java)
    private val mockGitHubRepo: GitHubRepoImpl = mock(GitHubRepoImpl::class.java)

    private lateinit var project: List<ProjectGitHubEntity>
    private lateinit var context: Context

    private lateinit var mockToast: Toast

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable ->
            Schedulers.trampoline()
        }

        // положительный ответ от репозитория
        project = listOf(
            ProjectGitHubEntity(
                id = 1,
                name = "mojombo",
                description = "description",
                userId = "userId",
                forksCount = 2,
                forksUrl = "forksUrl",
                private = false
            )
        )

        `when`(mockGitHubRepo.getProject(MOJOMBO_REPOS_API_URL)).thenReturn(Single.just(project))

        presenter = DetailsUserGitHubPresenter(
            gitHubRepo = mockGitHubRepo,
            reposUrl = MOJOMBO_REPOS_API_URL,
            context = context.applicationContext,
        )
        presenter.attachView(mockView)

        mockToast = mock(Toast::class.java)
    }

    @Test
    fun `test loadData renders view upon success response`() {

        // запустим метод загрузки данных
        presenter.loadData(MOJOMBO_REPOS_API_URL)

        // убедимся, что показаны индикатор загрузки
        verify(mockView, atLeastOnce()).showProgressBar()

        // убедимся, что метод обновления списка вызван с данными из репозитория
        verify(mockView, atLeastOnce()).updateProjectList(project)

        // убедимся, что прогресс индикатор скрыт
        verify(mockView, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun `test loadData renders view upon failure response`() {
        // отрицательный ответ от репозитория
        val error = Exception()
        `when`(mockGitHubRepo.getProject(MOJOMBO_REPOS_API_URL)).thenReturn(Single.error(error))

        // запустим метод загрузки данных
        presenter.loadData(MOJOMBO_REPOS_API_URL)

        // убедимся, что показаны индикатор загрузки
        verify(mockView, atLeastOnce()).showProgressBar()

        // убедимся, что прогресс индикатор скрыт
        verify(mockView, atLeastOnce()).hideProgressBar()
    }

    @Test
    fun testOnAttach() {
        presenter.onAttach()
    }

    @Test
    fun testOnDetach() {
        presenter.detachView(mockView)
        presenter.onDetach()
        assertNull(presenter.getContext())
    }
}