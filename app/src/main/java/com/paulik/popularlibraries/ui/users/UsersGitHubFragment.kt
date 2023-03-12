package com.paulik.popularlibraries.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulik.popularlibraries.App
import com.paulik.popularlibraries.data.UsersGitHubRepoImpl
import com.paulik.popularlibraries.databinding.FragmentUsersGitHubBinding
import com.paulik.popularlibraries.domain.UsersGitHubViewPresenter
import com.paulik.popularlibraries.domain.entity.UsersGitHubEntity
import com.paulik.popularlibraries.domain.interactor.NetworkStatusInteractor
import com.paulik.popularlibraries.rxjava.OtherConsumerError
import com.paulik.popularlibraries.ui.root.ViewBindingFragment
import com.paulik.popularlibraries.ui.users.adapter.UsersAdapter
import com.paulik.popularlibraries.ui.users.base.BackButtonListener
import com.paulik.popularlibraries.utils.snack
import moxy.ktx.moxyPresenter

class UsersGitHubFragment : ViewBindingFragment<FragmentUsersGitHubBinding>(
    FragmentUsersGitHubBinding::inflate
), UsersGitHubViewPresenter, BackButtonListener {

    private val app: App get() = requireActivity().applicationContext as App

    private val presenter by moxyPresenter {
        UsersGitHubPresenter(
            App.instance.router,
            UsersGitHubRepoImpl()
        )
    }

    private val networkStatusInteractor: NetworkStatusInteractor by lazy {
        app.networkStatusInteractor
    }

    private val adapter by lazy {
        UsersAdapter {
            presenter.onUserClicked(requireContext(), it)
        }

//        UsersAdapter(presenter::onUserClicked)// вариант записи
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        networkStatus()

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
//        Consumer().subscribeZip()
//        Consumer().subscribeInterval()
//        OtherConsumer().subscribeCompletable()
//        OtherConsumer().subscribeSingle()
//        OtherConsumer().subscribeMaybe()

//        OtherConsumer().subscribeTime1() // пример с двумя подписчиками. получение подписчиками данных с нуля
//        OtherConsumer().subscribeTime2() // пример с двумя подписчиками. Данные не с нуля. Горячая подписка.
//        OtherConsumer().subscribeReplay() // -/- подгрузка пропущенных данных
//        OtherConsumer().subscribeRefCount() // -/- Получение вторым подписчиком данных с того момента с которого он подписался.
//        OtherConsumer().subscribeCache() // -/- Работает при 1 подписке, хранит элем. и отдает все элем. каждому новому подписчику.

//        OtherConsumerSubjects().subscribe() // Subject

        // Многопоточность
//        OtherConsumerThreads().subscribe() // Interval
//        OtherConsumerThreads().subscribeJust() // Just. Для хождения в сеть, скачивания файлов и т.д.
//        OtherConsumerThreads().subscribeJustComputation() // Just. Создает солько Thread сколько есть ядер у процессора
//        OtherConsumerThreads().subscribeCreateMainThread() // Create. Разные потоки
//        OtherConsumerThreads().subscribeCreateNewThread() // Create. Свой Thread
//        OtherConsumerError().subscribeCreateErrorReturn() // Create. Обработка ошибки onErrorReturn
//        OtherConsumerError().subscribeCreateErrorResumeNext() // Create. Обработка ошибки onErrorResumeNext
//        OtherConsumerError().subscribeCreateErrorResumeNextRetry() // Create. Обработка ошибки Retry
        OtherConsumerError().subscribeCreateDoOnErrorRetry() // Create. Обработка ошибки doOnError
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
        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList(users: List<UsersGitHubEntity>) {
        // submitList - отправляет список элементов
        adapter.submitList(users)
//        adapter.submitList(adapter.currentList + users) // вариант

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