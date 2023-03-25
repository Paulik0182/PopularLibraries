package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.cache.ProjectGitHubCache
import com.paulik.popularlibraries.data.cache.UsersGitHubCache
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GitHubRepoImpl(
    private val gitHubApi: GitHubApi,
    private val projectGitHubCache: ProjectGitHubCache,
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
//        return gitHubApi.getUsers()
    }

    override fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
//        return if (networkStatusInteractor.isOnLine()) {
//            /** если есть интернет */
//            gitHubApi.getProject(reposUrl)
//                .flatMap { projects ->
//                    projectGitHubCache.insertProject(projects)
//                }
//        } else {
//            /** если нет интернета */
//            projectGitHubCache.getProject(reposUrl)
//        }
        return gitHubApi.getProject(reposUrl)
    }

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return gitHubApi.getForks(forksUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}