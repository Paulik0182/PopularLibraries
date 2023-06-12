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

        initButton()
    }

    private fun initButton() {
        binding.counterMvpButton.setOnClickListener {
            getController().openCounterBasedMvp()
        }
        binding.usersGitHubButton.setOnClickListener {
            getController().openUsersGitHub()
        }
        binding.convertorImageButton.setOnClickListener {
            getController().openConverterImage()
        }
    }

    interface Controller {
        fun openCounterBasedMvp()
        fun openUsersGitHub()
        fun openConverterImage()
    }

    private fun getController(): Controller = activity as Controller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }
}