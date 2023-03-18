package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersGitHubPresenter(
    private val router: Router,
    private val usersGitHubRepoImpl: UsersGitHubRepoImpl
) : MvpPresenter<UsersGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        usersGitHubRepoImpl.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users: List<UsersGitHubEntity> ->
                viewState.updateList(users)
            }, {
                Log.e(
                    "Retrofit. UsersGitHubPresenter",
                    "Ошибка при получении списка пользователей",
                    it
                )

            })
    }

    fun onUserClicked(context: Context, usersGitHubEntity: UsersGitHubEntity) {
        viewState.showUser(usersGitHubEntity.login)

        Toast.makeText(context, usersGitHubEntity.login, Toast.LENGTH_SHORT).show()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}