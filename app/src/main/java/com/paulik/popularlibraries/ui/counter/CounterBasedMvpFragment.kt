package com.paulik.popularlibraries.ui.counter

import android.content.Context
import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.databinding.FragmentCounterBasedMvpBinding
import com.paulik.popularlibraries.ui.root.ViewBindingFragment

class CounterBasedMvpFragment : ViewBindingFragment<FragmentCounterBasedMvpBinding>(
    FragmentCounterBasedMvpBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    interface Controller {
        // todo
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}