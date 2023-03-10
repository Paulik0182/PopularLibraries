package com.paulik.popularlibraries.ui.users

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class UserGitHubActivity : MvpAppCompatActivity(R.layout.activity_users), UsersGitHubViewPresenter,
    UsersGitHubFragment.Controller {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { UsersRootPresenter(App.instance.router) }

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

        // если наш фрагмент евляется наследником BackButtonListener и backPressed возвращает true
        // то наше активити не нуждается в закрытии
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }

    override fun updateList() {
        // TODO("Not yet implemented")
    }
}