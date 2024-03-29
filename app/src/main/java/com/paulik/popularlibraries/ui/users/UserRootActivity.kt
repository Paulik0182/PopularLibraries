package com.paulik.popularlibraries.ui.users

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubFragment
import com.paulik.popularlibraries.ui.users.projects.ProjectGitHubMvpView
import com.paulik.popularlibraries.ui.users.projects.ProjectUserGitHubFragment
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserRootActivity : MvpAppCompatActivity(R.layout.activity_users), UsersGitHubMvpView,
    ProjectGitHubMvpView,
    UsersGitHubMvpFragment.Controller,
    ProjectUserGitHubFragment.Controller {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        App.instance.appComponent.usersRootPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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
    }

    override fun showReposUrl(reposUrl: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, ProjectUserGitHubFragment.newInstance(reposUrl)
            )
            .commit()
    }

    override fun showForksRepo(forksUrl: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container, ForksRepoGitHubFragment.newInstance(forksUrl)
            )
            .commit()
    }

    override fun updateUsersList(users: List<UsersGitHubEntity>) {
        // TODO("Not yet implemented")
    }

    override fun updateProjectList(project: List<ProjectGitHubEntity?>) {
        // TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        // TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        // TODO("Not yet implemented")
    }
}