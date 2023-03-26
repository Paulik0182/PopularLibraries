package com.paulik.popularlibraries.ui.users.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.databinding.FragmentDetailsUserGitHubBinding
import com.paulik.popularlibraries.domain.ProjectGitHubMvpView
import com.paulik.popularlibraries.domain.entity.ProjectGitHubEntity
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.UserRootActivity
import com.paulik.popularlibraries.ui.users.adapter.ProjectAdapter
import com.paulik.popularlibraries.ui.users.base.FragmentInitializer
import com.paulik.popularlibraries.ui.users.base.InitParams
import com.paulik.popularlibraries.ui.users.base.initParams
import moxy.ktx.moxyPresenter
import java.io.Serializable

class DetailsUserGitHubFragment : ViewBindingFragment<FragmentDetailsUserGitHubBinding>(
    FragmentDetailsUserGitHubBinding::inflate
), ProjectGitHubMvpView {

    private val presenter by moxyPresenter {
//        App.instance.initProjectSubcomponent()
//        App.instance.projectSubcomponent?.detailsUserGitHubPresenterFactory()!!
        App.instance.appComponent.detailsUserGitHubPresenterFactory()
            .detailsUserGitHubPresenterFactory(
                userUrl as String // вариант 2 (наиболее предпочтительный)
//                requireArguments().getString(this::class.java.name)!! // вариант 1 (не очень)
            )
    }

    private val userUrl: Serializable by initParams()
//    private val userUrl by initParams<String>() // вариант записи

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

    companion object : FragmentInitializer<InitParams>()

//    companion object {
//
//        @JvmStatic
//        fun newInstance(reposUrl: String) =
//            DetailsUserGitHubFragment().apply {
//                arguments = bundleOf(KEY_USER to reposUrl)
//            }
//    }

    override fun updateProjectList(project: List<ProjectGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(project)
    }

    override fun showForksRepo(forksUrl: String) {
        (requireActivity() as UserRootActivity).showForksRepo(forksUrl)
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