package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ForksRepoGitHubPresenter(
    private val gitHubRepoImpl: GitHubRepoImpl,
    private val forksUrl: String
) : MvpPresenter<ForksRepoGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(forksUrl)
    }

    @SuppressLint("CheckResult")
    fun loadData(forksUrl: String) {
        val forksSingle = gitHubRepoImpl.getForks(forksUrl)
        if (forksSingle != null) {
            forksSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgressBar()
                }
                .subscribe({ fork: List<ForksRepoGitHubEntity> ->
                    viewState.updateForksList(fork)
                    viewState.hideProgressBar()
                }, {
                    viewState.showError(it.message ?: "Неизвестная ошибка")
                })
        } else {
            viewState.showError("Ошибка получения данных с сервера")
            viewState.hideProgressBar()
        }
    }
}