package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.databinding.FragmentUsersGitHubBinding
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.rxjava.Consumer
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.UsersAdapter
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import moxy.ktx.moxyPresenter

class UsersGitHubFragment : ViewBindingFragment<FragmentUsersGitHubBinding>(
    FragmentUsersGitHubBinding::inflate
), UsersGitHubViewPresenter, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersGitHubPresenter(
            App.instance.router,
            UsersGitHubRepoImpl()
        )
    }

    private var flagVisibilityFragment = 0L

    private val adapter by lazy {
        UsersAdapter {
            presenter.onUserClicked(requireContext(), it)
        }

//        UsersAdapter(presenter::onUserClicked)// вариант записи
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

//        Consumer().subscribe()
//        Consumer().subscribeFromIterable()
////        Consumer().subscribeTimer()
//        Consumer().subscribeRange()
//        Consumer().subscribeFromCallable()
//        Consumer().subscribeCreate()
//        Consumer().subscribeIntervalTake()
//        Consumer().subscribeMap()
//        Consumer().subscribeDistinct()
//        Consumer().subscribeFilter()
//        Consumer().subscribeMarge()
//        Consumer().subscribeFlatMap()
        Consumer().subscribeZip()
//        Consumer().subscribeInterval()
    }

    private fun initView() {
        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList(users: List<UsersGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(users)
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

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}