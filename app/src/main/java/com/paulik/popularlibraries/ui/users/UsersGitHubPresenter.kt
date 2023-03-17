package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.data.users.GitHubApi
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersGitHubPresenter(
    private val router: Router,
    private val usersGitHubRepoImpl: UsersGitHubRepoImpl,
    private val gitHubApi: GitHubApi
) : MvpPresenter<UsersGitHubMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        val service = gitHubApi
        service.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { users: List<UsersGitHubEntity> ->
                viewState.updateList(users)
            }

//        usersGitHubRepoImpl.getUsers()
//            .subscribe {
//                viewState.updateList(it)
//            }

//        viewState.updateList(users)
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