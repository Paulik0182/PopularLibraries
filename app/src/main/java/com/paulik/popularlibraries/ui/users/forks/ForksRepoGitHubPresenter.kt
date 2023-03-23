package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class ForksRepoGitHubPresenter(
    private val forksUrl: String
) : MvpPresenter<ForksRepoGitHubMvpView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var gitHubRepoImpl: GitHubRepo

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