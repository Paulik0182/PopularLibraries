package com.paulik.popularlibraries.ui.users.details

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class DetailsUserGitHubPresenter(
    private val router: Router,
    private val gitHubRepoImpl: GitHubRepoImpl,
    private val reposUrl: String
) : MvpPresenter<ProjectGitHubMvpView>() {

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
                viewState.updateList(project)
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
//        viewState.showProject(projectGitHubEntity)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}