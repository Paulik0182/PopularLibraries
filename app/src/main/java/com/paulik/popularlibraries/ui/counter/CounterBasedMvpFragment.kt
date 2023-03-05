package com.paulik.popularlibraries.ui.counter

import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.R
import com.paulik.popularlibraries.databinding.FragmentCounterBasedMvpBinding
import com.paulik.popularlibraries.domain.CounterPresenter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CounterBasedMvpFragment : MvpAppCompatFragment(R.layout.fragment_counter_based_mvp),
    CounterPresenter {

    private var _binding: FragmentCounterBasedMvpBinding? = null
    private val binding get() = _binding!!

    // @InjectPresenter – аннотация для управления жизненным циклом Presenter
    @InjectPresenter // в старых версиях. Работает только с дефолтными значениями в конструкторе CounterBasedMvpPresenter
    lateinit var presenter: CounterBasedMvpPresenter

    @ProvidePresenter
    fun providePresenter(): CounterBasedMvpPresenter {
        return CounterBasedMvpPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCounterBasedMvpBinding.bind(view)

        binding.oneCounterButton.setOnClickListener {
            presenter.onOneCounterClicked()
        }

        binding.twoCounterButton.setOnClickListener {
            presenter.onTwoCounterClicked()
        }

        binding.threeCounterButton.setOnClickListener {
            presenter.onThreeCounterClicked()
        }
    }

    override fun setOneButtonText(text: String) {
        binding.oneCounterButton.text = text
    }

    override fun setTwoButtonText(text: String) {
        binding.twoCounterButton.text = text
    }

    override fun setThreeButtonText(text: String) {
        binding.threeCounterButton.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView(this)
    }
}