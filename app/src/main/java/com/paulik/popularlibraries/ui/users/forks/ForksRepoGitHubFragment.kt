package com.paulik.popularlibraries.ui.users.forks

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.databinding.FragmentForksRepoGitHubBinding
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.ForksRepoAdapter
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import moxy.ktx.moxyPresenter

private const val KEY_FORKS_REPO = "KEY_FORKS_REPO"

class ForksRepoGitHubFragment : ViewBindingFragment<FragmentForksRepoGitHubBinding>(
    FragmentForksRepoGitHubBinding::inflate
), ForksRepoGitHubMvpView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.initForkRepositorySubcomponent()
        App.instance.forkRepositorySubcomponent?.forksRepoGitHubPresenterFactory()
            ?.forksRepoPresenter(requireArguments().getString(KEY_FORKS_REPO)!!)!!
    }

    private val adapter by lazy {
        ForksRepoAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance(forksUrl: String?) =
            ForksRepoGitHubFragment().apply {
                arguments = bundleOf().apply {
                    putString(KEY_FORKS_REPO, forksUrl)
                }
            }
    }

    override fun updateForksList(forks: List<ForksRepoGitHubEntity?>) {
        // submitList - отправляет список элементов
        adapter.submitList(forks)
    }

    override fun showProgressBar() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}