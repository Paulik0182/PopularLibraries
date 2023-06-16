package com.paulik.popularlibraries.ui.users.forks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.data.room.RoomDb
import com.paulik.popularlibraries.databinding.FragmentForksRepoGitHubBinding
import com.paulik.popularlibraries.domain.ForksRepoGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ForksRepoGitHubEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.ForksRepoAdapter
import moxy.ktx.moxyPresenter

private const val KEY_FORKS_REPO = "KEY_FORKS_REPO"

class ForksRepoGitHubFragment : ViewBindingFragment<FragmentForksRepoGitHubBinding>(
    FragmentForksRepoGitHubBinding::inflate
), ForksRepoGitHubMvpView {

    private val app: App get() = requireActivity().applicationContext as App

    private val presenter by moxyPresenter {
        ForksRepoGitHubPresenter(
            gitHubRepo = GitHubRepoImpl(
                app.gitHubApi,
                RoomDb.instanceRoom,
                app.networkStatusInteractor
            ),
            forksUrl = requireArguments().getString(KEY_FORKS_REPO)!!,
            context = requireContext()
        )
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
        fun newInstance(forksUrl: String) =
            ForksRepoGitHubFragment().apply {
                arguments = bundleOf().apply {
                    putString(KEY_FORKS_REPO, forksUrl)
                }
            }
    }

    override fun updateForksList(forks: List<ForksRepoGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(forks)
    }

    override fun showFork(fork: String) {
        // TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
    }
}