package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ForksRepoGitHubPresenter(
    private val router: Router,
    private val gitHubRepoImpl: GitHubRepoImpl,
    private val forksUrl: String
) : MvpPresenter<ForksRepoGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(forksUrl)
    }

    @SuppressLint("CheckResult")
    private fun loadData(forksUrl: String) {
        gitHubRepoImpl.getForks(forksUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .subscribe({ fork: List<ForksRepoGitHubEntity> ->
                viewState.updateForksList(fork)
                viewState.hideProgressBar()
            }, {
                Log.e(
                    "Retrofit. ForksRepoGitHubPresenter",
                    "Ошибка при получении списка форков",
                    it
                )
                viewState.showProgressBar()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}