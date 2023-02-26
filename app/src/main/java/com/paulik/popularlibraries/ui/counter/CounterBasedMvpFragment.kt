package com.paulik.popularlibraries.ui.counter

import android.content.Context
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

        val listener = View.OnClickListener { view ->
            presenter.counterClick(view.id)
        }

        binding.oneCounterButton.setOnClickListener(listener)
        binding.twoCounterButton.setOnClickListener(listener)
        binding.threeCounterButton.setOnClickListener(listener)
    }

    interface Controller {
        // todo
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    override fun setButtonText(index: Int, text: String) = when (index) {
        0 -> binding.oneCounterButton.text = text
        1 -> binding.twoCounterButton.text = text
        2 -> binding.threeCounterButton.text = text
        else -> error("Неверный индекс")
    }
}