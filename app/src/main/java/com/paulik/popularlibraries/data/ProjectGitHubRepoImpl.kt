package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.ProjectGitHubRepo
import io.reactivex.rxjava3.core.Single
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
                .flatMap(projectGitHubCache::insertProject)
        } else {
            /** если нет интернета */
            projectGitHubCache.getProject(reposUrl)
        }
    }
}