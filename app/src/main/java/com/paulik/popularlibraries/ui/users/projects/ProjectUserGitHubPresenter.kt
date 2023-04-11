package com.paulik.popularlibraries.ui.users.projects

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.di.scope.containers.ProjectScopeContainer
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ProjectsUserGitHubPresenter @AssistedInject constructor(
    private val router: Router,
    private val projectGitHubRepo: ProjectGitHubRepo,
    private val projectScopeContainer: ProjectScopeContainer,
    @Assisted private val reposUrl: String
) : MvpPresenter<ProjectGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(reposUrl)
    }

    @SuppressLint("CheckResult")
    private fun loadData(reposUrl: String) {
        projectGitHubRepo.getProject(reposUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .subscribe({ project: List<ProjectGitHubEntity?> ->
                viewState.updateProjectList(project)
                viewState.hideProgressBar()
            }, {
                Log.e(
                    "Retrofit. DetailsUserGitHubPresenter",
                    "Ошибка при получении списка проетов пользователя",
                    it
                )
                viewState.showProgressBar()
            })
    }

    fun onProjectClicked(projectGitHubEntity: ProjectGitHubEntity) {
        viewState.showForksRepo(projectGitHubEntity.forksUrl)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        projectScopeContainer.destroyProjectRepositorySubcomponent()
        super.onDestroy()
    }
}

@AssistedFactory
interface ProjectsUserGitHubPresenterFactory {
    fun projectsUserPresenter(reposUrl: String): ProjectsUserGitHubPresenter
}