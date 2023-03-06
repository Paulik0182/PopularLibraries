package com.paulik.popularlibraries.ui.users

import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.adapter.UserItemView
import com.paulik.popularlibraries.ui.users.base.IListPresenter
import moxy.MvpPresenter

class UsersGitHubPresenter(
    private val usersGitHubRepoImpl: UsersGitHubRepoImpl
) : MvpPresenter<UsersGitHubViewPresenter>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        usersListPresenter.itemClickListener = {}
    }

    private fun loadData() {
        val users = usersGitHubRepoImpl.getUsers()
        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }


    class UsersListPresenter : IListPresenter<UserItemView> {

        val users = mutableListOf<UsersGitHubEntity>()

        override var itemClickListener: () -> Unit = {}

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }
}