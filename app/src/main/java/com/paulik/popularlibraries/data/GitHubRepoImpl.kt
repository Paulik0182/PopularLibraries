package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GitHubRepoImpl(
//    private val networkStatusInteractorImpl: NetworkStatusInteractorImpl,
    private val gitHubApi: GitHubApi,
    private val db: RoomDb
) : GitHubRepo {

    override fun getUsers(): Single<List<UsersGitHubEntity>> {
//        return if (networkStatusInteractorImpl.isOnLine()) {
        /** если есть интернет */
        return gitHubApi.getUsers()
            // переключаемся на другой Observable
            .flatMap { users ->
                Single.fromCallable {
                    // необходимо поработать на другом Thread. трансформируем каждого из пользователя
                    val roomUsers = users.map { user ->
                        UsersGitHubEntity(
                            user.id,
                            user.login,
                            user.reposUrl,
                            user.avatarUrl,
                            user.nodeId
                        )
                    }
                    db.usersGitHubDao.saveUser(roomUsers)
                    users
                }
            }
//        } else {
//            /** если нет интернета */
//            Single.fromCallable {
//                db.usersGitHubDao.getAllUsers().map { user ->
//                    UsersGitHubEntity(
//                        user.id,
//                        user.login,
//                        user.reposUrl,
//                        user.avatarUrl,
//                        user.nodeId
//                    )
//                }
//            }
//        }
//        return gitHubApi.getUsers()
    }

    override fun getProject(reposUrl: String): Single<List<ProjectGitHubEntity>> {
//        return if (networkStatusInteractorImpl.isOnLine()) {
//            /** если есть интернет */
//            return gitHubApi.getProject(reposUrl)
//                // переключаемся на другой Observable
//                .flatMap { projects ->
//                    Single.fromCallable {
//                        // необходимо поработать на другом Thread. трансформируем каждого из пользователя
//                        val roomProject = projects.map { project ->
//                            ProjectGitHubEntity(
//                                project.id,
//                                project.name,
//                                project.description,
//                                project.userId,
//                                project.forksCount
//                            )
//                        }
//                        db.projectGitHubDao.saveProject(roomProject)
//                        projects
//                    }
//                }
//        } else {
//            /** если нет интернета */
//            Single.fromCallable {
//                db.projectGitHubDao.getAllProject().map { project ->
//                    ProjectGitHubEntity(
//                        project.id,
//                        project.name,
//                        project.description,
//                        project.userId,
//                        project.forksCount
//                    )
//                }
//            }
//        }
        return gitHubApi.getProject(reposUrl)
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}