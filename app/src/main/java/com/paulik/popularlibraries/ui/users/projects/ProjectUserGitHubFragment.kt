package com.paulik.popularlibraries.ui.users.projects

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.databinding.FragmentDetailsUserGitHubBinding
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.ProjectAdapter
import moxy.ktx.moxyPresenter

private const val KEY_USER = "KEY_USER"

class ProjectUserGitHubFragment : ViewBindingFragment<FragmentDetailsUserGitHubBinding>(
    FragmentDetailsUserGitHubBinding::inflate
), ProjectGitHubMvpView {

    private val presenter by moxyPresenter {
        App.instance.initProjectRepositorySubcomponent()
        App.instance.projectRepositorySubcomponent?.projectUserGitHubPresenterFactory()
            ?.projectsUserPresenter(requireArguments().getString(KEY_USER)!!)!!
    }

    private val adapter by lazy {
        ProjectAdapter(
            presenter::onProjectClicked,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    interface Controller {
        fun showForksRepo(forksUrl: String?)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {

        @JvmStatic
        fun newInstance(reposUrl: String) =
            ProjectUserGitHubFragment().apply {
                arguments = bundleOf(KEY_USER to reposUrl)
            }
    }

    override fun updateProjectList(project: List<ProjectGitHubEntity?>) {
        // submitList - отправляет список элементов
        adapter.submitList(project)
    }

    override fun showForksRepo(forksUrl: String?) {
        getController().showForksRepo(forksUrl)
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