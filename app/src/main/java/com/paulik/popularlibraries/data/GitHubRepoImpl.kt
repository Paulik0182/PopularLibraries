package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.room.RoomDb
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
    private val db: RoomDb,
    private val networkStatusInteractor: NetworkStatusInteractor
) : GitHubRepo {

    override fun getUsers(): Single<List<UsersGitHubEntity>> {
        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getUsers()
                // переключаемся на другой Observable
                .flatMap { users ->
                    Single.fromCallable {
                        // необходимо поработать на другом Thread. трансформируем каждого из пользователя
                        val roomUsers = users.map { user ->
                            UsersGitHubEntity(
                                id = user.id,
                                login = user.login,
                                reposUrl = user.reposUrl,
                                avatarUrl = user.avatarUrl,
                                nodeId = user.nodeId
                            )
                        }
                        db.usersGitHubDao().saveUser(roomUsers)
                        users
                    }
                }
        } else {
            /** если нет интернета */
            Single.fromCallable {
                db.usersGitHubDao().getAllUsers().map { user ->
                    UsersGitHubEntity(
                        id = user.id,
                        login = user.login,
                        reposUrl = user.reposUrl,
                        avatarUrl = user.avatarUrl,
                        nodeId = user.nodeId
                    )
                }
            }
        }
//        return gitHubApi.getUsers()
    }

    override fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
        return if (networkStatusInteractor.isOnLine()) {
            /** если есть интернет */
            gitHubApi.getProject(reposUrl)
                // переключаемся на другой Observable
                .flatMap { projects ->
                    Single.fromCallable {
                        // необходимо поработать на другом Thread. трансформируем каждого из пользователя
                        val roomProject = projects.map { project ->
                            ProjectGitHubEntity(
                                id = project.id,
                                name = project.name,
                                description = project.description,
                                userId = project.userId,
                                forksCount = project.forksCount,
                                forksUrl = project.forksUrl,
                                private = project.private
                            )
                        }
                        db.projectGitHubDao().saveProject(roomProject)
                        projects
                    }
                }
        } else {
            /** если нет интернета */
            Single.fromCallable {
                db.projectGitHubDao().getAllProject().map { project ->
                    ProjectGitHubEntity(
                        id = project.id,
                        name = project.name,
                        description = project.description,
                        userId = project.userId,
                        forksCount = project.forksCount,
                        forksUrl = project.forksUrl,
                        private = project.private
                    )
                }
            }
        }
//        return gitHubApi.getProject(reposUrl)
    }

    override fun getForks(forksUrl: String): Single<List<ForksRepoGitHubEntity>> {
        return gitHubApi.getForks(forksUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}