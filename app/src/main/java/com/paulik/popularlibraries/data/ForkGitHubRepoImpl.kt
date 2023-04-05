package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.ForkGitHubCache
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ForkGitHubRepoImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val forkGitHubCache: ForkGitHubCache,
    private val networkStatusInteractor: NetworkStatusInteractor
) : ForksGitHubRepo {

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {

        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getForks(forksUrl)
                .flatMap(forkGitHubCache::insertForks)
        } else {
            /** если нет интернета */
            forkGitHubCache.getFork(forksUrl)
        }
    }
}