package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ForksRepoGitHubPresenter @AssistedInject constructor(
    private val router: Router,
    private val forksGitHubRepo: ForksGitHubRepo,
    @Assisted private val forksUrl: String
) : MvpPresenter<ForksRepoGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(forksUrl)
    }

    @SuppressLint("CheckResult")
    private fun loadData(forksUrl: String) {
        forksGitHubRepo.getForks(forksUrl)
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

@AssistedFactory
interface ForksRepoGitHubPresenterFactory {
    fun forksRepoPresenter(forksUrl: String): ForksRepoGitHubPresenter
}