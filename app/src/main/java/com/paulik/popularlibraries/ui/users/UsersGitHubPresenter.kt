package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersGitHubPresenter(
    private val usersGitHubRepo: GitHubRepo
) : MvpPresenter<UsersGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    @SuppressLint("CheckResult")
    fun loadData() {
        val userSingle = usersGitHubRepo.getUsers()
        if (userSingle != null) {
            userSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.showProgressBar()
                }
                .subscribe({ users: List<UsersGitHubEntity> ->
                    viewState.updateUsersList(users)
                    viewState.hideProgressBar()
                }, {
                    viewState.showErrorMessage(it.message ?: "Неизвестная ошибка")
                    viewState.showProgressBar()
                })
        } else {
            viewState.showErrorMessage("Ошибка получения данных от сервера")
            viewState.hideProgressBar()
        }

    }

    fun onUserClicked(usersGitHubEntity: UsersGitHubEntity) {
        viewState.showReposUrl(usersGitHubEntity.reposUrl)
    }

    fun backPressed(): Boolean {
        return true
    }
}