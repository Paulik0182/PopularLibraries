package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.ForksGitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForksGitHubRepoImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val networkStatusInteractor: NetworkStatusInteractor
) : ForksGitHubRepo {

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return gitHubApi.getForks(forksUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}