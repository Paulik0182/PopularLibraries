package com.paulik.popularlibraries.ui.users.details

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsUserGitHubPresenter(
    private val reposUrl: String
) : MvpPresenter<ProjectGitHubMvpView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var gitHubRepoImpl: GitHubRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(reposUrl)
    }

    @SuppressLint("CheckResult")
    private fun loadData(reposUrl: String) {
        gitHubRepoImpl.getProject(reposUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .subscribe({ project: List<ProjectGitHubEntity> ->
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