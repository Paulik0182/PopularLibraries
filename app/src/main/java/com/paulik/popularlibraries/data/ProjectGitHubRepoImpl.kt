package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.domain.entity.project.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProjectGitHubRepoImpl @Inject constructor(
    private val gitHubApi: GitHubApi,
    private val projectGitHubCache: ProjectGitHubCache,
    private val networkStatusInteractor: NetworkStatusInteractor
) : ProjectGitHubRepo {

    override fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getProject(reposUrl)
                .flatMap { projects ->
                    projectGitHubCache.insertProject(projects)
                }
        } else {
            /** если нет интернета */
            projectGitHubCache.getProject(reposUrl)
        }
//        return gitHubApi.getProject(reposUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}