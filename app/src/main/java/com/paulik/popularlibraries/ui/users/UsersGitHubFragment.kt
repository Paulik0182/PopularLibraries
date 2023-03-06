package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.databinding.FragmentUsersGitHubBinding
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.UsersAdapter
import moxy.ktx.moxyPresenter


class UsersGitHubFragment : ViewBindingFragment<FragmentUsersGitHubBinding>(
    FragmentUsersGitHubBinding::inflate
), UsersGitHubViewPresenter {

    private val presenter by moxyPresenter { UsersGitHubPresenter(UsersGitHubRepoImpl()) }

    private val adapter by lazy {
        UsersAdapter(presenter.usersListPresenter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    interface Controller {
        // todo
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersGitHubFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}