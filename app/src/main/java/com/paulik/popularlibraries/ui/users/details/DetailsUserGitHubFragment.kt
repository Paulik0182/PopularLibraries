package com.paulik.popularlibraries.ui.users.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.GitHubRepoImpl
import com.paulik.popularlibraries.databinding.FragmentDetailsUserGitHubBinding
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.ProjectAdapter
import moxy.ktx.moxyPresenter

private const val KEY_USER = "KEY_USER"

class DetailsUserGitHubFragment : ViewBindingFragment<FragmentDetailsUserGitHubBinding>(
    FragmentDetailsUserGitHubBinding::inflate
), ProjectGitHubMvpView {

    private val app: App get() = requireActivity().applicationContext as App

    private lateinit var projectGitHubEntity: ProjectGitHubEntity

    private val presenter by moxyPresenter {
        DetailsUserGitHubPresenter(
            App.instance.router,
            GitHubRepoImpl(app.gitHubApi),
            requireActivity().getString(KEY_USER)!!
        )
    }

    private val adapter by lazy {
        ProjectAdapter(
            presenter::onProjectClicked,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

//        projectGitHubEntity = requireActivity().getString(KEY_USER)!!// todo чтото не так
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance(user: String) =
            DetailsUserGitHubFragment().apply {
                arguments = bundleOf(KEY_USER to user)
            }
    }

    override fun updateList(project: List<ProjectGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(project)
    }

    override fun showProject(project: String) {
        // TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
    }
}