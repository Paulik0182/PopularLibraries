package com.paulik.popularlibraries.ui.counter

import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.databinding.FragmentCounterBasedMvpBinding
import com.paulik.popularlibraries.domain.CounterPresenter
import com.paulik.popularlibraries.ui.root.ViewBindingFragment

class CounterBasedMvpFragment : ViewBindingFragment<FragmentCounterBasedMvpBinding>(
    FragmentCounterBasedMvpBinding::inflate
), CounterPresenter {

    private val presenter = CounterBasedMvpPresenter(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        presenter.detach()
    }
}