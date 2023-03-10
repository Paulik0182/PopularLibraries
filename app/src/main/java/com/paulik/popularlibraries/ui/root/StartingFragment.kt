package com.paulik.popularlibraries.ui.root

import android.content.Context
import android.os.Bundle
import android.view.View
import com.paulik.popularlibraries.databinding.FragmentStartingBinding

class StartingFragment : ViewBindingFragment<FragmentStartingBinding>(
    FragmentStartingBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.counterMvpButton.setOnClickListener {
            getController().openCounterBasedMvp()
        }
        binding.blanksButton.setOnClickListener {
            getController().openUsersGitHub()
//            view.snack("Пока никак")
        }
    }

    interface Controller {
        fun openCounterBasedMvp()
        fun openUsersGitHub()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}