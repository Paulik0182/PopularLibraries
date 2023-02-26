package com.paulik.popularlibraries.ui.root

import android.content.Context
import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.databinding.FragmentStartingBinding
import com.paulik.popularlibraries.utils.snack

class StartingFragment : ViewBindingFragment<FragmentStartingBinding>(
    FragmentStartingBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.counterMvpButton.setOnClickListener {
            getController().openCounterBasedMvp()
        }
        binding.blanksButton.setOnClickListener {
            view.snack("Пока никак")
        }
    }

    interface Controller {
        fun openCounterBasedMvp()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}