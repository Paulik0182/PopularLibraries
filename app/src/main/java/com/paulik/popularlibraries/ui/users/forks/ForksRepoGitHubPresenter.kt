package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.di.scope.containers.GithubForksScopeContainer
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
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
    @Assisted private val forksUrl: String,
    private val githubForksScopeContainer: GithubForksScopeContainer
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

    override fun onDestroy() {
        githubForksScopeContainer.destroyForksSubcomponent()
        super.onDestroy()
    }
}

@AssistedFactory
interface ForksRepoGitHubPresenterFactory {
    fun forksRepoPresenter(forksUrl: String): ForksRepoGitHubPresenter
}