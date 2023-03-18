package com.paulik.popularlibraries.ui.users

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment
import com.paulik.popularlibraries.ui.users.details.DetailsUserRootPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class UserGitHubMvpActivity : MvpAppCompatActivity(R.layout.activity_users), UsersGitHubMvpView,
    UsersGitHubMvpFragment.Controller {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { UsersRootPresenter(App.instance.router) }
    private val presenterDetails by moxyPresenter { DetailsUserRootPresenter(App.instance.router) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // если наш фрагмент является наследником BackButtonListener и backPressed возвращает true
        // то наше активити не нуждается в закрытии
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
        presenterDetails.backPressed()
    }

    override fun showUser(user: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, DetailsUserGitHubFragment.newInstance(user)
            )
//            .addToBackStack(null)
            .commit()
    }

    override fun updateList(users: List<UsersGitHubEntity>) {
        // TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        // TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        // TODO("Not yet implemented")
    }
}