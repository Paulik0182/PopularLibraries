package com.paulik.popularlibraries.ui.users.details

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class DetailsUserGitHubPresenter @AssistedInject constructor(
    private val router: Router,
    private val projectGitHubRepoImpl: ProjectGitHubRepo,
    @Assisted private val reposUrl: String
) : MvpPresenter<ProjectGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(reposUrl)
    }

    @SuppressLint("CheckResult")
    private fun loadData(reposUrl: String) {
        projectGitHubRepoImpl.getProject(reposUrl)
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
}

@AssistedFactory
interface DetailsUserGitHubPresenterFactory {
    fun detailsUserGitHubPresenterFactory(reposUrl: String): DetailsUserGitHubPresenter
}