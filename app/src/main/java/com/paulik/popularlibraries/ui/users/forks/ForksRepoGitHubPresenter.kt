package com.paulik.popularlibraries.ui.users.forks

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import com.paulik.popularlibraries.utils.BaseMvpPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ForksRepoGitHubPresenter(
    private val gitHubRepo: GitHubRepo,
    private val forksUrl: String,
    context: Context
) : BaseMvpPresenter<ForksRepoGitHubMvpView>() {

    private val myContext: Context = context.applicationContext

    override fun onAttach() {
        Toast.makeText(
            myContext,
            "ForksRepoGitHubPresenter: onAttach",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDetach() {
        Toast.makeText(
            myContext,
            "ForksRepoGitHubPresenter: onAttach",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData(forksUrl)
    }

    @SuppressLint("CheckResult")
    fun loadData(forksUrl: String) {
        gitHubRepo.getForks(forksUrl).subscribeOn(Schedulers.io())
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
    }
}