package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersGitHubPresenter(
    private val usersGitHubRepoImpl: GitHubRepoImpl
) : MvpPresenter<UsersGitHubMvpView>() {

    @Inject
    lateinit var router: Router
//    @Inject
//    lateinit var usersGitHubRepoImpl: GitHubRepoImpl

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        usersGitHubRepoImpl.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .subscribe({ users: List<UsersGitHubEntity> ->
                viewState.updateUsersList(users)
                viewState.hideProgressBar()
            }, {
                Log.e(
                    "Retrofit. UsersGitHubPresenter",
                    "Ошибка при получении списка пользователей",
                    it
                )
                viewState.showProgressBar()
            })
    }

    fun onUserClicked(usersGitHubEntity: UsersGitHubEntity) {
        viewState.showReposUrl(usersGitHubEntity.reposUrl)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}