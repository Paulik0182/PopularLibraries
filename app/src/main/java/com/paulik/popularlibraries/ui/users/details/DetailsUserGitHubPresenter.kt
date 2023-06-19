package com.paulik.popularlibraries.ui.users.details

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.domain.repo.GitHubRepo
import com.paulik.popularlibraries.utils.BaseMvpPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsUserGitHubPresenter(
    private var gitHubRepo: GitHubRepo,
    private val reposUrl: String,
    context: Context,
) : BaseMvpPresenter<ProjectGitHubMvpView>() {

    private var myContext: Context? = context.applicationContext

    override fun onAttach() {
        Toast.makeText(
            myContext,
            "DetailsUserGitHubPresenter: onAttach",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDetach() {
        Toast.makeText(
            myContext,
            "DetailsUserGitHubPresenter: onDetach",
            Toast.LENGTH_SHORT
        ).show()
        myContext = null
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData(reposUrl)
    }

    @SuppressLint("CheckResult")
    fun loadData(reposUrl: String) {
        gitHubRepo.getProject(reposUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgressBar() }
            .subscribe({ project: List<ProjectGitHubEntity> ->
                viewState.updateProjectList(project)
                viewState.hideProgressBar()
            }, {
                Log.e(
                    "Retrofit. DetailsUserGitHubPresenter",
                    "Ошибка при получении списка проетов пользователя",
                    it
                )
                viewState.showProgressBar()
            })
    }

    fun onProjectClicked(projectGitHubEntity: ProjectGitHubEntity) {
        viewState.showForksRepo(projectGitHubEntity.forksUrl)
    }

    fun getContext(): Context? {
        return myContext
    }
}