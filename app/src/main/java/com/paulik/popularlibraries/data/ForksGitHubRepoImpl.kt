package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.ForksGitHubCache
import com.paulik.popularlibraries.domain.entity.forks.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForksGitHubRepoImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val forksGitHubCache: ForksGitHubCache,
    private val networkStatusInteractor: NetworkStatusInteractor
) : ForksGitHubRepo {

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {

        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getForks(forksUrl)
                .flatMap { forks ->
                    forksGitHubCache.insertForks(forks)
                }
        } else {
            /** если нет интернета */
            forksGitHubCache.getForks(forksUrl)
        }
//        return gitHubApi.getForks(forksUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}