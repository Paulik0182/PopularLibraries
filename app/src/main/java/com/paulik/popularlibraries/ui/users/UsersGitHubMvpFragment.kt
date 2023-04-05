package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.databinding.FragmentUsersGitHubBinding
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.root.image.GlideImageLoader
import com.paulik.popularlibraries.ui.users.adapter.UsersAdapter
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import com.paulik.popularlibraries.utils.snack
import moxy.ktx.moxyPresenter

class UsersGitHubMvpFragment : ViewBindingFragment<FragmentUsersGitHubBinding>(
    FragmentUsersGitHubBinding::inflate
), UsersGitHubMvpView, BackButtonListener {

    private val app: App get() = requireActivity().applicationContext as App
    private val networkStatusInteractor: NetworkStatusInteractor by lazy {
        app.networkStatusInteractor
    }

    private val presenter by moxyPresenter {
        App.instance.initUsersRepositorySubcomponent()
        App.instance.usersRepositorySubcomponent?.usersGitHubPresenter()!!
    }

    private val adapter by lazy {
        UsersAdapter(
            presenter::onUserClicked,
            GlideImageLoader()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        networkStatus()
    }

    @SuppressLint("CheckResult")
    private fun networkStatus() {
        networkStatusInteractor.getNetworkStatusSubject().subscribe {
            Log.d("Rxjava", "Состояние сети: $it")
            if (!it) {
                view?.snack("Интернета  Нет!!")
            } else {
                view?.snack("Интернет подключен")
            }
        }
    }

    private fun initView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsersList(users: List<UsersGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(users)
//        adapter.submitList(adapter.currentList + users) // вариант

    }

    interface Controller {
        fun showReposUrl(reposUrl: String)
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersGitHubMvpFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun showReposUrl(reposUrl: String) {
        getController().showReposUrl(reposUrl)
//        (requireActivity() as UserRootActivity).showReposUrl(reposUrl)
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