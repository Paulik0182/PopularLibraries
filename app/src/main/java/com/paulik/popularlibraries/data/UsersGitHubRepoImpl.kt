package com.paulik.popularlibraries.data

import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.repo.UsersGitHubRepo
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class UsersGitHubRepoImpl : UsersGitHubRepo {

    private val usersGitHub = listOf(
        UsersGitHubEntity(login = "user1"),
        UsersGitHubEntity(login = "user2"),
        UsersGitHubEntity(login = "user3"),
        UsersGitHubEntity(login = "user4"),
        UsersGitHubEntity(login = "user5"),
        UsersGitHubEntity(login = "user6"),
        UsersGitHubEntity(login = "user7")
    )

    override fun getUsers(): Observable<List<UsersGitHubEntity>> {
        return Observable.just(usersGitHub)

//        return Observable.create {emitter ->
//            usersGitHub.forEach {
//                emitter.onNext(it)
//            }
//        }
    }

    fun interval(): @NonNull Observable<Long> {
        return Observable.interval(1, TimeUnit.SECONDS)
    }
}