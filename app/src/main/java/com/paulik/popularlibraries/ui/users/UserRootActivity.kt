package com.paulik.popularlibraries.ui.users

import android.widget.Toast
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.UsersGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubFragment
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class UserRootActivity : MvpAppCompatActivity(R.layout.activity_users), UsersGitHubMvpView,
    ProjectGitHubMvpView,
    UsersGitHubMvpFragment.Controller {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { UsersRootPresenter(App.instance.router) }
//    private val presenterDetails by moxyPresenter { DetailsUserRootPresenter(App.instance.router) }

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
//        presenterDetails.backPressed()
    }

    override fun showReposUrl(reposUrl: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, DetailsUserGitHubFragment.newInstance(reposUrl)
            )
//            .addToBackStack(null)
            .commit()
    }

    override fun showForksRepo(forksUrl: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, ForksRepoGitHubFragment.newInstance(forksUrl)
            )
//            .addToBackStack(null)
            .commit()
    }

    override fun updateUsersList(users: List<UsersGitHubEntity>) {
        // TODO("Not yet implemented")
    }

    override fun updateProjectList(project: List<ProjectGitHubEntity>) {
        // TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        // TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        // TODO("Not yet implemented")
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}