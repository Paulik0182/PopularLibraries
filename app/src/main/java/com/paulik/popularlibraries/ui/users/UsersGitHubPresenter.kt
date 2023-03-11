package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment
import moxy.MvpPresenter

class UsersGitHubPresenter(
    private val router: Router,
    private val usersGitHubRepoImpl: UsersGitHubRepoImpl
) : MvpPresenter<UsersGitHubViewPresenter>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        usersGitHubRepoImpl.getUsers()
            .subscribe {
                viewState.updateList(it)
            }

//        viewState.updateList(users)
    }

    fun onUserClicked(context: Context, usersGitHubEntity: UsersGitHubEntity) {
        DetailsUserGitHubFragment.newInstance()

        Toast.makeText(context, usersGitHubEntity.login, Toast.LENGTH_SHORT).show()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}