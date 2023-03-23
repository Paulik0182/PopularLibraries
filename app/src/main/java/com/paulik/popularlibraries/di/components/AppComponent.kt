package com.paulik.popularlibraries.di.components

import com.paulik.popularlibraries.di.modules.*
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import com.paulik.popularlibraries.ui.users.UserRootActivity
import com.paulik.popularlibraries.ui.users.UsersGitHubMvpFragment
import com.paulik.popularlibraries.ui.users.UsersGitHubPresenter
import com.paulik.popularlibraries.ui.users.UsersRootPresenter
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubFragment
import com.paulik.popularlibraries.ui.users.details.DetailsUserGitHubPresenter
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubFragment
import com.paulik.popularlibraries.ui.users.forks.ForksRepoGitHubPresenter
import dagger.Component

@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(userRootActivity: UserRootActivity)

    fun inject(usersGitHubMvpFragment: UsersGitHubMvpFragment)
    fun inject(usersRootPresenter: UsersRootPresenter)
    fun inject(usersGitHubPresenter: UsersGitHubPresenter)

    fun inject(detailsUserGitHubFragment: DetailsUserGitHubFragment)
    fun inject(detailsUserGitHubPresenter: DetailsUserGitHubPresenter)

    fun inject(forksRepoGitHubFragment: ForksRepoGitHubFragment)
    fun inject(forksRepoGitHubPresenter: ForksRepoGitHubPresenter)

    fun inject(gitHubRepo: GitHubRepo)
}