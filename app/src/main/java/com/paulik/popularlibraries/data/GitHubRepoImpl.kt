package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GitHubRepoImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val usersGitHubCache: UsersGitHubCache,
    private val networkStatusInteractor: NetworkStatusInteractor
) : GitHubRepo {

    override fun getUsers(): Single<List<UsersGitHubEntity>> {
        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getUsers()
                .flatMap(usersGitHubCache::insertUsers)
        } else {
            /** если нет интернета */
            usersGitHubCache.getUser()
        }
    }

    override fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
        return gitHubApi.getProject(reposUrl)
    }

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return gitHubApi.getForks(forksUrl)
    }
}